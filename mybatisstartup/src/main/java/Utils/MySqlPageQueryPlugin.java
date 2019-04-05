package Utils;

import Definitions.PageParams;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public class MySqlPageQueryPlugin implements Interceptor {

    private Logger logger = Logger.getLogger(MySqlPageQueryPlugin.class);
    
    // 默认页码
    private Integer defaultPageNo;
    // 默认每页条数
    private Integer defaultPageSize;
    // 默认是否启用插件
    private Boolean defaultUseFlag;
    // 默认是否检测页码参数
    private Boolean defaultCheckFlag;
    // 默认是否清除最后一个 order by 后的语句
    private Boolean defaultCleanOrderBy;

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) getUnPrxyObject(invocation.getTarget());
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        if(!checkIsSelect(sql)){
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue ("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        PageParams pageParams =  getPageParamsForParamObj(parameterObject);
        if(pageParams == null){
            return invocation.proceed();
        }

        boolean useFlag = pageParams.isUseFlag() == null ? this.defaultUseFlag : pageParams.isUseFlag();
        if(!useFlag){
            return invocation.proceed();
        }
        Integer pageNo = pageParams.getPageNo() == null?this.defaultPageNo:pageParams.getPageNo();
        Integer pageSize = pageParams.getPageSize() == null?this.defaultPageSize:pageParams.getPageSize();
        Boolean checkFlag = pageParams.isCheckPageNoLessThanSize()==null?this.defaultCheckFlag:pageParams.isCheckPageNoLessThanSize();
        Boolean cleanOrderBy = pageParams.isCleanOrderBy()==null?this.defaultCleanOrderBy:pageParams.isCleanOrderBy();
        // 获取记录总条数
        int total = getTotal(invocation, metaStatementHandler, boundSql, cleanOrderBy);
        pageParams.setTotalRecord(total);
        int totalPage = total % pageSize == 0?total/pageSize:total/pageSize+1;
        pageParams.setTotalPage(totalPage);;
        checkPage(checkFlag, pageNo, totalPage);
        logger.debug("Page select plugin working....pageNo:【"+pageNo+"】, pageSize:【" +pageSize+"】");
        return preparedSQL(invocation, metaStatementHandler, boundSql, pageNo, pageSize);
    }

    /**
     *  从代理对象中分离出真实对象
     * @param target  --Invocation
     * @return     非代理 StatementHandler 对象
     */
    private Object getUnPrxyObject(Object target) throws Throwable {
        // 进行绑定
        MetaObject metaStatementHandler = SystemMetaObject.forObject(target);
        Object obj = null;
        //  分离代理对象链（由于目标类可能被多个拦截器［插件］拦截， 从而形成多次代理，通过循环可以分离出最原始的目标类）
        while (metaStatementHandler.hasGetter("h")) {
            obj = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(obj);
        }
        while (obj instanceof Plugin){
            // 利用反射
            Field field = obj.getClass().getDeclaredField("target");
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            obj = field.get(obj);
        }
        if(obj == null){
            return target;
        }
        return obj ;
    }

    /**
     * 分离出分页参数
     * @param parameterObj 执行参数
     * @return  分页参数
     */
    private PageParams getPageParamsForParamObj(Object parameterObj) throws Exception {
        if (parameterObj == null){
            return  null;
        }
        if(parameterObj instanceof Map){ //处理 map 参数， 多个匿名参数和 @Param 注解参数，都是 map
            @SuppressWarnings ("unchecked")
            Map<String, Object> paramMap = (Map<String, Object>)parameterObj;
            Set<String> keySet = paramMap.keySet();
            for (String k : keySet) {
                Object value = paramMap.get(k);
                if (value instanceof PageParams) {
                    return (PageParams) value;
                }
            }
        }
        else if(parameterObj instanceof PageParams){  // 参数自己就是或者继承自 PageParams
            return (PageParams)parameterObj;
        }
        else {   // 从 POJO 属性尝试读取分页参数
            Field[] fields = parameterObj.getClass().getDeclaredFields();
            // 尝试从 POJO 中获得类型为 PageParams 的属性
             for (Field f : fields){
                 if(f.getType() == PageParams.class){
                     // 利用反射
                     PropertyDescriptor pd = new PropertyDescriptor(f.getName() , parameterObj.getClass());
                     Method method = pd.getReadMethod();
                     return (PageParams)method.invoke(parameterObj);
                 }
             }
        }
        return null;
    }

    /**
     * 获取总条数
     * @param invocation   Invocation 入参
     * @param metaStatementHandler   statementHandler 
     * @param boundSql   sql
     * @param cleanOrderBy   是否消除 order by 语句
     * @return  sql 查询总数
     */
    private int getTotal(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, boolean cleanOrderBy) throws Exception {
        // 获取当前的 mappedStatement
        MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement") ;
        Configuration cfg = mappedStatement.getConfiguration();
        String sql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
        if(cleanOrderBy){
            // 删除 order by 语句是因为它会影响 SQL 的执行性能
            sql = cleanOrderByForSql(sql);
        }
        String countSql = "select count(*) as total from (" + sql +") $_paging";
        // 获取拦截方法参数，根据插件签名，知道是 Connection 对象
        // StatementHandler 的参数（第一个是 Connection ， 第二个是超时整数）
        Connection connection = (Connection)invocation.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0;
        try{
            ps = connection.prepareStatement(countSql);
            // 构建统计总数 BoundSql
            BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            // 构建 MyBatis 的 ParameterHandler 用来设置总数 Sql 的参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            // 设置总数 SQL 参数
            parameterHandler.setParameters(ps);
            // 执行查询
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                total = rs.getInt("total");
            }
        } finally {
            // 这里不能关闭 Connection ，否则后续的 SQL 就没法继续了
            if(ps != null){
                ps.close();
            }
        }
        return total;
    }

    private void checkPage(Boolean checkFlag, int pageNo, int totalPage) throws Throwable {
          if(checkFlag){
              if(pageNo > totalPage){
                  throw new Exception ("查询失败，查询页码 【"+ pageNo +"】大于总页数 【"+ totalPage +"】");
              }
          }
    }

    /**
     * 预编译改写后的 SQL ，并设置分页参数
     * @param invocation
     * @param metaStatementHandler   MetaObject 绑定的 StatementHandler
     * @param boundSql
     * @param pageNo  当前页
     * @param pageSize  每页记录数
     * @return
     */
    private Object preparedSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql,int pageNo, int pageSize) throws InvocationTargetException, IllegalAccessException, SQLException {
        // 获取当前需要执行的 SQL
        String sql = boundSql.getSql();
        String newSql = "select * from (" + sql + ") $_paging_table limit ?, ?";
        metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
        // 执行编译，相当于 StatementHandler 执行了 prepared() 方法 ，这个时候，就剩下两个分页参数没有设置
        // 实际就是调用了 StatementHandler 的 prepare 方法，这个方法的作用就是预编译 SQL 的参数
        Object statementObj = invocation.proceed();
        // 设置两个分页参数
        PreparedStatement ps = (PreparedStatement)statementObj;
        int idx = ps.getParameterMetaData().getParameterCount();
        // 最后两个是分页参数
        ps.setInt(idx-1, (pageNo-1)*pageSize); // 开始行
        ps.setInt(idx, pageSize); // 限制条数
        return statementObj;
    }

    private String cleanOrderByForSql(String sql){
        StringBuilder sb = new StringBuilder(sql);
        String newSql = sql.toLowerCase();
        int idx = newSql.lastIndexOf("order");
        // 如果没有 order by 语旬，则直接返回
        if (idx == -1) {
            return sql;
        }
        return sb. substring(0, idx);
    }
    
    /**
     * 检查是否 select 语句
     * @param sql
     * @return
     */
    private boolean checkIsSelect(String sql){
        return sql.trim().toLowerCase().startsWith("select");
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {
        // 获取并设置在配置文件中自定义的参数
        String strDefaultPageNo = properties.getProperty("default.pageNo","1");
        String strDefaultPageSize = properties.getProperty("default.pageSize","1");
        String slrDefaultsFlag = properties.getProperty("default.useFlag","false");
        String strDefaultCheckFlag = properties.getProperty("default.checkPageNoLessThanSize","false");
        String strDefaultCleanOrderBy = properties.getProperty("default.isCleanOrderBy","false");

        this.defaultPageNo = Integer.parseInt(strDefaultPageNo);
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
        this.defaultUseFlag = Boolean.parseBoolean(slrDefaultsFlag);
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
        this.defaultCleanOrderBy = Boolean.parseBoolean(strDefaultCleanOrderBy);
    }
}

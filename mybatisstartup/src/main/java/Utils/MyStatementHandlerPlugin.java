package Utils;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public class MyStatementHandlerPlugin implements Interceptor {

    private Logger logger = Logger.getLogger(MyStatementHandlerPlugin.class);

    private Properties props = null;

    /**
     * 插件方法，它将代替 StatementHandler 的 prepare 方法
     * @param invocation  入参
     * @return    返回预编译后的 PreparedStatement .
     * @throws Throwable   异常
     */
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 进行绑定
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        Object obj = null;
        //  分离代理对象链（由于目标类可能被多个拦截器［插件］拦截， 从而形成多次代理，通过循环可以分离出最原始的目标类）
        while (metaStatementHandler.hasGetter("h")) {
            obj = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(obj);
        }
        statementHandler = (StatementHandler) obj ;
        String sql = (String) metaStatementHandler.getValue ("delegate.boundSql.sql");
        Object parameterObj = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        logger.info("执行的SQL：【" + sql + "】");
        logger.info("参数：【" + parameterObj + "】");
        logger.info("before ...................................................");
        // 如果当前代理的是一个非代理对象，那么它就回调用其实拦截对象的方法
        // 如果不是，那么它会调度下个插件代理对象的 invoke 方法
        Object object = invocation.proceed();
        logger.info("after.......................................................");
        return object;
    }

    /**
     * 生成代理对象
     * @param o 被拦截对象
     * @return   代理对象
     */
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    /**
     * 设置参数， MyBatis 初始化时，就会生成插件实例，并且调用这个方法
     * @param properties 配置参数
     */
    public void setProperties(Properties properties) {
           this.props = properties;
        logger.info("dbType = "+ this.props.get ("db Type"));
    }
}

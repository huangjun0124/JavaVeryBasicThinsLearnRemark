package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    private final static Class<SqlSessionFactoryUtil> LOCK =  SqlSessionFactoryUtil.class ;
    
    private static SqlSessionFactory sqlSessionFactory = null;

    private SqlSessionFactoryUtil(){}

    public static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            if(sqlSessionFactory != null){
                return sqlSessionFactory;
            }
            //配置文件
            String resource = "mybatis-config.xml";
            //配置文件流
            InputStream inputStream;
            try{
                 inputStream = Resources.getResourceAsStream(resource);
                //创建会话工厂
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession openSqlSession(){
        if(sqlSessionFactory == null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}

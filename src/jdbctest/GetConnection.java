package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //1.注册驱动 反射技术,将驱动类加入到内容
        // 使用java.sql.DriverManager类静态方法 registerDriver(Driver driver)
        // Diver是一个接口,参数传递,MySQL驱动程序中的实现类
        //DriverManager.registerDriver(new Driver());
        //驱动类源代码,注册2次驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");// com.mysql.jdbc.Driver deprecated
        String serverName = "192.168.44.129";
        String database = "test1";
        String url = "jdbc:mysql://" + serverName + "/" + database;

        // 数据配置用户和密码
        String user = "keith";
        String password = "KeithP@ss666";

        return DriverManager.getConnection(url, user, password);
    }
}

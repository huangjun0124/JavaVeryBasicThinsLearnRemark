package jdbctest;

import java.sql.*;

public class MySqlTest {
    public static void main(String[] args){
        try {

            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            // 查询city表中所有的数据
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {
                while (resultSet.next()) {
                    // 依次打印出查询结果中Name的字符串
                    System.out.println(resultSet.getString("Name"));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        String serverName = "192.168.44.129";
        String database = "test1";
        String url = "jdbc:mysql://" + serverName + "/" + database;

        // 数据配置用户和密码
        String user = "keith";
        String password = "KeithP@ss666";

        return DriverManager.getConnection(url, user, password);
    }
}

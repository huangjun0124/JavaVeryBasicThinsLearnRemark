package jdbctest;

import com.company.DateFormat;
import com.company.SimpleConsoleLog;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class ClassRegiTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DoInsert();
        SimpleConsoleLog.WriteLine("请输入要update的用户名：");
        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();
        String sql = "UPDATE user set createtime=CURRENT_TIME(), password=? where name=?";
        Connection con = GetConnection.getConnection();
        //调用数据库连接对象con的方法prepareStatement获取SQL语句的预编译对象
        PreparedStatement pst = con.prepareStatement(sql);
        //调用pst的方法setXXX设置?占位
        pst.setObject(1, "pas" + DateFormat.FormatDate(new Date()) );
        pst.setObject(2, user);
        //调用pst方法执行SQL语句
        int row = pst.executeUpdate();
        SimpleConsoleLog.WriteLine(row + " rows updated");
        pst.close();
        con.close();
    }

    private static void DoInsert() {
        try {
            String sql = "insert into user (name,password,email,birthday,CreateTime)\n" +
                    "values(" +
                    "    '插入测试'," +
                    "    'password'," +
                    "    'nihao@ss.com'," +
                    "    '2050-12-04'," +
                    "    CURRENT_TIME()" +
                    "  );";

            Connection connection = GetConnection.getConnection();
            try (Statement statement = connection.createStatement()) {
                int row = statement.executeUpdate(sql);
                SimpleConsoleLog.WriteLine(row + " rows affected");
                //statement.close();  not needed
            }
            connection.close(); // needed
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}

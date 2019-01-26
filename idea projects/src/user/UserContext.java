package user;

import DBUtil.GetConnection;
import user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

public class UserContext {

    public static User getUserById(String userId) {
        String sql = "select * from t_user where userid= ? ";
        User user = null;
        try
        {
            Connection con = GetConnection.getConnection();
            con.setAutoCommit(false);
            //调用数据库连接对象con的方法prepareStatement获取SQL语句的预编译对象
            PreparedStatement pst = con.prepareStatement(sql);
            //调用pst的方法setXXX设置?占位
            pst.setString(1, userId );
            //调用pst方法执行SQL语句
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setUserId(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setCreationDate(rs.getTimestamp(6));
                pst.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static int addUser(User user){
        String sql = "insert into t_user(userid, username, password,email,creationdate)values(?,?,?,?,?)";
        int ret = 0;
        Connection con = null;
        try
        {
            con = GetConnection.getConnection();
            con.setAutoCommit(false); // 相当于 begin tran
            //调用数据库连接对象con的方法prepareStatement获取SQL语句的预编译对象
            PreparedStatement pst = con.prepareStatement(sql);
            //调用pst的方法setXXX设置?占位
            pst.setString(1, user.getUserId() );
            pst.setString(2, user.getUserName() );
            pst.setString(3, user.getPassword() );
            pst.setString(4, user.getEmail() );
            pst.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
            //调用pst方法执行SQL语句
            ret = pst.executeUpdate();
            con.commit();
            pst.close();
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
            if(con != null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

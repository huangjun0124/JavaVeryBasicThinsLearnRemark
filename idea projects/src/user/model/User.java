package user.model;

public class User {
    private int id;
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}

/*
建表语句
CREATE TABLE t_user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userid VARCHAR(50) NOT NULL,
	password VARCHAR(50),
	username nvarchar(100) ,
  email VARCHAR(50),
	creationdate datetime
)

INSERT INTO t_user(userid,password,username,email,creationdate)
VALUES('admin','123','Administrator','admin@qq.com','1990-09-11 19:20:12');
INSERT INTO t_user(userid,password,username,email,creationdate)
VALUES('jun','123','俊果果','gyf@qq.com','2019-09-11 19:50:30');
 */

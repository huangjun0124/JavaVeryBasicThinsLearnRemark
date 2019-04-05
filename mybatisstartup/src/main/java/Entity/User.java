package Entity;

import Definitions.AccountTypeEnum;
import Definitions.SexEnum;

public class User {
    private String id;
    private String userName;
    private SexEnum sex;
    private AccountTypeEnum accountType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public String toString(){
        return  "UserBean{" +
                "id='" + id + '\'' +
                ",userName='" + userName + '\'' +
                ",sex='" + sex.getName() + '\'' +
                ",AccountType='" + accountType + '\'' +
                "}";
    }
}

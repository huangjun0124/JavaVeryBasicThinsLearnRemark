package com.keith.aspectj;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
    @Override
    public void addUser() {
        System.out.println("添加用户，处理中。。。。。。。。。。");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户，处理中------------------");
    }
}

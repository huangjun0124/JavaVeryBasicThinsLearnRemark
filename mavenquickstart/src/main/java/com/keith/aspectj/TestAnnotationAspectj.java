package com.keith.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotationAspectj {
    public static void doTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("file:src/main/java/com/keith/aspectj/applicationContext.xml");
        // 1 从 spring 容器获得内容
        IUserDao userDao = (IUserDao) applicationContext.getBean( "userDao");
        // 2 执行方法
        userDao.addUser();
        System.out.println("********************************************************************");
        userDao.deleteUser();
    }
}

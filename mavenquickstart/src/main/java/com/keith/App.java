package com.keith;

import com.keith.aspectj.TestAnnotationAspectj;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "开始启用 AspectJ 注解方式测试----------------------------------------" );
        TestAnnotationAspectj.doTest();
    }
}

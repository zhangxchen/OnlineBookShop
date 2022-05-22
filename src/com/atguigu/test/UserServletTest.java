package com.atguigu.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login(){
        System.out.println("这是login()方法调用了");
    }
    public void regist(){
        System.out.println("这是regist()方法调用了");
    }
    public void UpdateUserPassword(){
        System.out.println("这是UpdateUserPassword()方法调用了");
    }

    public static void main(String[] args) {
        String action = "login";
        try {
            //通过action业务鉴别字符串 获取响应的业务方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
//            System.out.println(method);
            //调用目标业务方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

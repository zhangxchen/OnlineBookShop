package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"周生辰","11","zhoushengchen@126.cpm"));
        userService.registUser(new User(null,"时宜","11","shiyi@126.cpm"));
    }

    /**
     * 登录
     * 如果返回null说明登录失败
     */
    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"周生辰","11",null)));
    }

    @Test
    public void existsUserName() {
        if(userService.existsUsername("周生教授")){
            System.out.println("用户名已存在！");
        }else{
            System.out.println("用户名可用！");
        }
    }
}
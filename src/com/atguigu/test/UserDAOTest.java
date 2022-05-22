package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

public class UserDAOTest {
    UserDao userDAO = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if(userDAO.queryUserByUsername("zxc1")==null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDAO.saveUser(new User(null,"zxc2","123456","zxc@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
       if( userDAO.queryUserByUsernameAndPassword("admin","admin") == null){
           System.out.println("用户名或密码错误，登录失败！");
       }else{
           System.out.println("登陆成功");
       }
    }
}
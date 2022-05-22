package com.atguigu.test;

import com.atguigu.pojo.Manager;
import com.atguigu.service.ManagerService;
import com.atguigu.service.impl.ManagerServiceImpl;
import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerServiceTest {
    /*
    final Integer managerId, final String managerPassword, final String managerName,
            final String managerEmail
     */
    ManagerService managerService = new ManagerServiceImpl();

    @Test
    public void registManager() {
        managerService.registManager(new Manager(null,"111111","qaqrz","qaqrz@126.com"));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void login() {
        System.out.println(managerService.login(new Manager(null, "111111", "qaqrz",
                "qaqrz@126.com")));
    }

    @Test
    public void existsManagerName() {
        System.out.println(managerService.existsManagerName("admin"));
    }
}
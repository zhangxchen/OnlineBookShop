package com.atguigu.test;

import com.atguigu.dao.ManagerDAO;
import com.atguigu.dao.impl.ManagerDAOImpl;
import com.atguigu.pojo.Manager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerDAOTest {
    private ManagerDAO managerDAO = new ManagerDAOImpl();
    @Test
    public void queryManagerByName() {
        System.out.println(managerDAO.queryManagerByName("admin"));
    }

    @Test
    public void queryManagerByNameAndPassword() {
        System.out.println(managerDAO.queryManagerByNameAndPassword("admin", "admin"));
    }

    @Test
    public void saveManager() {
        managerDAO.saveManager(new Manager(2,"123456","zxc","zxc@126.com"));
    }
}
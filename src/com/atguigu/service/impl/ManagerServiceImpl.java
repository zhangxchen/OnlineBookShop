package com.atguigu.service.impl;

import com.atguigu.dao.ManagerDAO;
import com.atguigu.dao.impl.ManagerDAOImpl;
import com.atguigu.pojo.Manager;
import com.atguigu.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDAO managerDAO = new ManagerDAOImpl();

    @Override
    public void registManager(final Manager manager) {
        managerDAO.saveManager(manager);
    }

    @Override
    public Manager login(final Manager manager) {
        return managerDAO.queryManagerByNameAndPassword(manager.getManagerName(),
                manager.getManagerPassword());
    }

    @Override
    public boolean existsManagerName(final String managerName) {
        if(managerDAO.queryManagerByName(managerName) != null){
            return true;
        }
        return false;
    }
}

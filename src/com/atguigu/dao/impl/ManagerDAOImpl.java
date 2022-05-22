package com.atguigu.dao.impl;

import com.atguigu.dao.ManagerDAO;
import com.atguigu.pojo.Manager;
import com.atguigu.pojo.User;

public class ManagerDAOImpl extends BaseDao implements ManagerDAO {

    @Override
    public Manager queryManagerByName(final String managerName) {
        String sql = "select `manager_id` managerId,`manager_name` managerName,`manager_password` managerPassword," +
                "`manager_email` managerEmail from " +
                "t_manager where `manager_name` = ?";
        return queryForOne(Manager.class, sql, managerName);
    }

    @Override
    public Manager queryManagerByNameAndPassword(final String managerName,
            final String managerPassword) {
        String sql = "select `manager_id` managerId,`manager_name` managerName,`manager_password`" +
                " managerPassword,`manager_email` managerEmail from t_manager where " +
                "`manager_name` = ? and `manager_password` = ?";
        return queryForOne(Manager.class, sql, managerName,managerPassword);
    }

    @Override
    public int saveManager(final Manager manager) {
        String sql = "insert into t_manager(`manager_name`,`manager_password`,`manager_email`) values(?,?,?)";
        return update(sql, manager.getManagerName(),manager.getManagerPassword(),manager.getManagerEmail());
    }
}

package com.atguigu.dao;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.Manager;
import com.atguigu.pojo.User;

public interface ManagerDAO{
    /**
     * 根据管理员用户查询管理员用户信息
     * @param managerName 管理员用户名
     * @return 如果返回null,说明没有这个管理员用户。反之亦然
     */
    public Manager queryManagerByName(String managerName);

    /**
     * 根据 管理员用户名和密码查询管理员用户信息
     * @param managerName
     * @param managerPassword
     * @return 如果返回null,说明管理员用户名或密码错误,反之亦然
     */
    public Manager queryManagerByNameAndPassword(String managerName, String managerPassword);

    /**
     * 保存管理员用户信息
     * @param manager
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveManager(Manager manager);
}

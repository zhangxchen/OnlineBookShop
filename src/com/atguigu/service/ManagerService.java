package com.atguigu.service;

import com.atguigu.pojo.Manager;
import com.atguigu.pojo.User;

public interface ManagerService {
    /**
     * 注册管理员
     * @param manager
     */
    public void registManager(Manager manager);

    /**
     * 登录
     * @param manager
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public Manager login(Manager manager);

    /**
     * 检查 用户名是否可用
     * @param managerName
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsManagerName(String managerName);
}

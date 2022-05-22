package com.atguigu.web;

import com.atguigu.pojo.Manager;
import com.atguigu.pojo.User;
import com.atguigu.service.ManagerService;
import com.atguigu.service.impl.ManagerServiceImpl;
import com.atguigu.utils.WebUtils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServlet extends BaseServlet{
    ManagerService managerService = new ManagerServiceImpl();
    /**
     * 管理员登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //  1、获取请求的参数
        String managerName = req.getParameter("managerName");
        String password = req.getParameter("password");
        // 调用 userService.login()登录处理业务
        Manager loginManager = managerService.login(new Manager(null, managerName, password, null));
        // 如果等于null,说明登录 失败!
        if (loginManager == null) {
            //把错误信息和回显的表单项信息保存在request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("managerName",managerName);
            //   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            //跳到成功页面login_success.html
            req.getSession().setAttribute("manager",loginManager);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }


    /**
     * 管理员注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logOut(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

    }

}

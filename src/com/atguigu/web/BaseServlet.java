package com.atguigu.web;

import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/baseServlet")
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //解决post请求中文乱码的问题（在获取请求参数前调用）
        req.setCharacterEncoding("UTF-8");
        //解决响应的中文乱码
        resp.setContentType("text/html; charset=UTF-8");
        final String action = req.getParameter("action");

        try {
            final Method method = this.getClass().getDeclaredMethod(action,
                    HttpServletRequest.class,
                    HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给 TransactionFilter 过滤器
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req,resp);
    }
}

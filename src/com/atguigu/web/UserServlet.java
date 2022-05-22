package com.atguigu.web;

import com.atguigu.pojo.Manager;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;
import com.atguigu.service.ManagerService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.ManagerServiceImpl;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private ManagerService managerService = new ManagerServiceImpl();

    /**
     * 用户注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logOut(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、销毁session中用户登录的信息（或者销毁session）
        req.getSession().invalidate();
        //2、重定向到希望显示的页面
        resp.sendRedirect(req.getContextPath()+"/pages/user/manager_login.jsp");

    }

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //  1、获取请求的参数
        String loginIdentity = req.getParameter("loginIdentity");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if("普通用户".equals(loginIdentity)){
            // 调用 userService.login()登录处理业务
            User loginUser = userService.login(new User(null, username, password, null));
            // 如果等于null,说明登录 失败!
            if (loginUser == null) {
                //把错误信息和回显的表单项信息保存在request域中
                req.setAttribute("msg","用户名或密码错误！");
                req.setAttribute("username",username);
                //   跳回登录页面
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } else {
                // 登录 成功
                //跳到成功页面login_success.html
                req.getSession().setAttribute("user",loginUser);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            }
        }else if("管理员".equals(loginIdentity)){
            // 调用 managerService.login()登录处理业务
            Manager loginManager = managerService.login(new Manager(null, password,username,
                    null));
            // 如果等于null,说明登录 失败!
            if (loginManager == null) {
                //把错误信息和回显的表单项信息保存在request域中
                req.setAttribute("msg","用户名或密码错误！");
                req.setAttribute("username",username);
                //   跳回登录页面
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } else {
                // 登录 成功
                //跳到成功页面login_success.html
//                req.getSession().setAttribute("user",loginManager);
                req.getRequestDispatcher("/pages/manager/managerLogin_success.jsp").forward(req,
                        resp);
            }
        }
    }

    /**
     * 使用AJAX请求异步判断用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取请求的参数
        String username = req.getParameter("username");
        //2、调用userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);
        //3、把结果封装成为map对象，回传给客户端
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * AJAX请求的注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxRegist(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //假如表单成功提交 说明：
        //（1）用户名可用
        //（2）密码格式正确 且 两次密码输入一致
        //（3）邮箱格式正确
        //（4）验证码不为空
        //此时需要检查 验证码输入是否正确
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码！
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //获取请求参数中的验证码
        String code = req.getParameter("code");
        if(token.equalsIgnoreCase(code)){
            //验证码正确
            //调用Service保存到数据库
            userService.registUser(new User(null, req.getParameter("username"), req.getParameter(
                    "password"), req.getParameter("email")));
            //跳到注册成功页面 regist_success.jsp
            req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
        }else{
            //把回显信息保存到request域中
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",req.getParameter("username"));
            req.setAttribute("email",req.getParameter("email"));
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
//    protected void regist(final HttpServletRequest req, final HttpServletResponse resp)
//            throws ServletException, IOException {
//        //获取session中的验证码
//        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        //删除session中的验证码！
//        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
//
//        //1、获取请求的参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String code = req.getParameter("code");
//
//        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
//
//
//        //2、检查验证码是否正确
//         if (token!=null && token.equalsIgnoreCase(code)) {
//        //3、检查 用户名是否可用
//            if (userService.existsUsername(username)) {
//                //把回显信息保存到request域中
//                req.setAttribute("msg","用户名已存在!");
//                req.setAttribute("username",username);
//                req.setAttribute("email",email);
//
//                System.out.println("用户名[" + username + "]已存在!");
//        //跳回注册页面
//                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//            } else {
//                //可用
//                //调用Service保存到数据库
//                userService.registUser(new User(null, username, password, email));
//                //跳到注册成功页面 regist_success.jsp
//                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
//            }
//        } else {
//            //把回显信息保存到request域中
//            req.setAttribute("msg","验证码错误");
//            req.setAttribute("username",username);
//            req.setAttribute("email",email);
//
//            System.out.println("验证码[" + code + "]错误");
//            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//        }
//    }
}

package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 根据用户编号查询未发货订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showUndeliveredOrdersByUserId(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取参数
        Integer userId = WebUtils.parseInt(req.getParameter("userId"),0);
        //2、orderService.showUndeliveredOrdersByUserId(userId)
        List<Order> orders = orderService.showUndeliveredOrdersByUserId(userId);
        req.getSession().setAttribute("clicked",true);
        req.getSession().setAttribute("orders",orders);
        req.getSession().setAttribute("userId",userId);
        req.getRequestDispatcher("/pages/manager/managerLogin_success.jsp").forward(req,resp);
        //3、把结果封装成为map对象，回传给客户端
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("undeliveredOrders",orders);

//        Gson gson = new Gson();
//        String json = gson.toJson(1);
//        resp.getWriter().write(json);
    }
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2、获取UserId
        User loginUser = (User) req.getSession().getAttribute("user");
        //检查用户是否登录
        if(loginUser == null){
            //还没有登录，跳到登录页面
            req.getRequestDispatcher("/pages/user/manager_login.jsp").forward(req,resp);
            return;
        }
        //打印当前线程名
        System.out.println("OrderServlet程序在【" + Thread.currentThread().getName() + "】线程中");
        //3、调用OrderService的createOrder(Cart,UserId)
        String orderId = orderService.createOrder(cart, loginUser.getId());
        req.getSession().setAttribute("orderId",orderId);
        //4、请求转发到结算页面(重定向不支持request域中保存数据)
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 查看所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        req.getRequestDispatcher("/pages/manager/managerLogin_success.jsp").forward(req,resp);
    }

    /**
     * 查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取订单的编号
        String orderId = req.getParameter("orderId");
        //2、调用OrderService的showOrderDetails(String orderId)方法查询订单项信息
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        //3、把orderItems和order的金额保存在session域中
        req.getSession().setAttribute("orderItems",orderItems);
        req.getSession().setAttribute("orderPrice",orderService.showOrderPriceByOrderId(orderId));
        //4、转发至/pages/order/order_details.jsp
        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req,resp);
    }

    /**
     * 管理员查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void managerShowOrderDetail(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        //1、获取订单的编号
        String orderId = req.getParameter("orderId");
        //2、调用OrderService的showOrderDetails(String orderId)方法查询订单项信息
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        //3、把orderItems和order的金额保存在session域中
        req.getSession().setAttribute("orderItems",orderItems);
        req.getSession().setAttribute("orderPrice",orderService.showOrderPriceByOrderId(orderId));
        //4、转发至/pages/order/order_details.jsp
        req.getRequestDispatcher("/pages/manager/manager_order_details.jsp").forward(req,resp);
    }

    /**
     * 查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取用户的id
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer loginUserId = loginUser.getId();
        //2、调用OrderService的showMyOrders(userId)方法查询该用户的订单信息
        List<Order> orders = orderService.showMyOrders(loginUserId);
        //3、把orders保存至session域中
        req.getSession().setAttribute("orders",orders);
        //4、转发到订单页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 签收
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取订单编号
        String orderId = req.getParameter("orderId");
        //2、调用orderService.receiverOrder()方法
        orderService.receiverOrder(orderId);
        //3、转发至/pages/cart/cart.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
}

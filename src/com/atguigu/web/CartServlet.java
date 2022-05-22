package com.atguigu.web;

import com.atguigu.dao.BookDAO;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    /**
     * 用AJAX请求实现加入购物车功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取商品编号
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);

        //2、调用bookService.queryBookById()得到图书的信息
        Book book = bookService.queryBookById(id);

        //3、检查库存
        Map<String,Object> resultMap = new Hashtable<>();

        if(book.getStock() != 0){
            //4、把book对象转化成为cartItem
            CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
            //5、获取session中的购物车对象Cart
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if(cart == null){
                cart = new Cart();
                req.getSession().setAttribute("cart",cart);
            }
            //6、调用cart.addItem()添加商品项
            cart.addItem(cartItem);
            System.out.println(cart);
//            req.getSession().setAttribute("lastName",cartItem.getName());

            //7、返回购物车总的商品数量和最后一个添加的商品名称
            resultMap.put("totalCount",cart.getTotalCount());
            resultMap.put("lastName",cartItem.getName());
            resultMap.put("isSoldOut",false);

        }else{
            resultMap.put("bookName",book.getName());
            resultMap.put("isSoldOut",true);
        }

        Gson gson = new Gson();
        String resultMaoJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMaoJsonString);
    }
    /**
     * 添加商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //获取请求的参数 商品编号
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //检查商品的库存
        //调用bookService.queryBookById()得到图书的信息
        Book book = bookService.queryBookById(id);
        //检查商品的库存
        if(book.getStock() != 0){
            //把图书信息转化为CartItem商品项
            //库存和销量的变化在结账时修改
            CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
            //调用Cart.addItem(CartItem)加入购物车
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if(cart == null){
                cart = new Cart();
                req.getSession().setAttribute("cart",cart);
            }
            cart.addItem(cartItem);
            System.out.println(cart);
            //最后一个添加的商品名称
            req.getSession().setAttribute("lastName",cartItem.getName());
        }
        System.out.println("请求头Referer的值" + req.getHeader("Referer"));
        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //从session域中获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来购物车的展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、从session域中获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2、清空购物车
        if(cart != null){
            cart.clear();
            //重定向回原来购物车的展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取请求参数（修改为几件,商品编号）
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);
        //2、获取cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //3、判断库存是否符合要求
        Book updateBook = bookDAO.queryBookById(id);
        if(updateBook.getStock() >= count){
            //4、修改商品数量
            if(cart != null){
                cart.updateCount(id,count);
            }
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}

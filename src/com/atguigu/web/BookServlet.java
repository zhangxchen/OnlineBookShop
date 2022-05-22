package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * 添加
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        //  1、获取请求的参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //  2、调用bookService的添加方法
        bookService.addBook(book);
        //  3、跳到图书列表页面/manager/bookServlet?action=list
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //  1、获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    /**
     * 保存修改图书的操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //  1、获取请求的参数 封装成为Book对象
        final Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //  2、调用BookService.updateBook(book) 修改图书
        bookService.updateBook(book);
        //  3、重定向回图书列表管理页面/工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
    }

    /**
     * 展示图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        req.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{
        //1、获取请求参数
        final int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2、调用BookService的queryBookById（id）方法 得到修改图书信息
        final Book book = bookService.queryBookById(id);
        //3、把图书信息保存到request域中
        req.setAttribute("book",book);
        //4、请求转发到/pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{
        //1、获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用bookService.page(pageNo,pageSize) 返回page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page",page);
        //4、请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}

package com.atguigu.service.impl;

import com.atguigu.dao.BookDAO;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = new BookDAOImpl();
    @Override
    public void addBook(final Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBookById(final Integer id) {
        bookDAO.deleteBookById(id);
    }

    @Override
    public void updateBook(final Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(final Integer id) {
        return bookDAO.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //page对象
        Page<Book> page = new Page<>();

        //每页显示的数据量
        page.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDAO.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        
        //当前页码
        page.setPageNo(pageNo);


            //当前页数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDAO.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page pageByPrice(final int pageNo, final int pageSize, final int min, final int max) {
        //page对象
        Page<Book> page = new Page<>();

        //每页显示的数据量
        page.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDAO.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        //总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //当前页码
        page.setPageNo(pageNo);


        //当前页数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDAO.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}

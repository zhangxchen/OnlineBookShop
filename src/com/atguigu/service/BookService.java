package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除图书
     * @param Id
     */
    public void deleteBookById(Integer id);

    /**
     * 修改图书
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 查询图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 处理分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 处理前台价格区间分页
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public Page pageByPrice(int pageNo,int pageSize,int min,int max);

}

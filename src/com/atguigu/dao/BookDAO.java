package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDAO {
    /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 删除图书
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 修改图书
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 查询图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询总页数
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询每页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 前台价格区间分页总记录数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     *查询价格区间内的记录
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}

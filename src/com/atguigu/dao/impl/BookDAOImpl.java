package com.atguigu.dao.impl;

import com.atguigu.dao.BookDAO;
import com.atguigu.pojo.Book;

import java.util.List;

public class BookDAOImpl extends BaseDao implements BookDAO {
    @Override
    public int addBook(final Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`)" +
                "values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),
                book.getStock(),
                book.getImgPath());
    }

    @Override
    public int deleteBookById(final Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(final Book book) {

        System.out.println("BookDAOImpl程序在【" + Thread.currentThread().getName() + "】线程中");

        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?," +
                "`img_path`=? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),
                book.getStock(),
                book.getImgPath(),book.getId());
    }

    
    @Override
    public Book queryBookById(final Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from" +
                " t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from" +
                " t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(final int begin, final int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from" +
                " t_book " +
                "limit" + " ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(final int min, final int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number countByPrice = (Number) queryForSingleValue(sql, min, max);
        return countByPrice.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(final int begin, final int pageSize, final int min,
            final int max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath " +
                " from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }

}

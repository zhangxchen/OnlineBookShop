package com.atguigu.test;

import com.atguigu.dao.BookDAO;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDAOTest {
    private BookDAO bookDAO = new BookDAOImpl();
    @Test
    public void addBook() {
        bookDAO.addBook(new Book(null,"一生一世美人骨","墨宝非宝",new BigDecimal(999),111111,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDAO.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDAO.updateBook(new Book(21,"一生一世美人骨","墨宝非宝",new BigDecimal(9999),111111,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDAO.queryBookById(21));
    }

    @Test
    public void queryBooks() {
       for(Book book : bookDAO.queryBooks()) {
           System.out.println(book);
       }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDAO.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDAO.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItems() {
        final List<Book> books = bookDAO.queryForPageItems(8, 4);
        for(Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItemsByPrice() {
        final List<Book> books = bookDAO.queryForPageItemsByPrice(0, 4,10,50);
        for(Book book : books){
            System.out.println(book);
        }
    }
}
package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"周生如故","墨宝非宝",new BigDecimal(999),111111,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(25);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(24,"一生一世","墨宝非宝",new BigDecimal(999),111111,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(24));
    }

    @Test
    public void queryBooks() {
        for(Book book : bookService.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}
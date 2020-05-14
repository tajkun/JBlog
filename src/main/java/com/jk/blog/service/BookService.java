package com.jk.blog.service;

import com.jk.blog.po.Blog;
import com.jk.blog.po.Book;
import com.jk.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-05-12 14:35
 **/
public interface BookService {

    Book getBookById(Long bookId);

    Book getBookByTitle(String title);

    Page<Book> listBook(Pageable pageable);

    Page<Book> listBookByType(Type type, Pageable pageable);
//    Page<Book> listBook(String query,Pageable pageable);

    List<Book> listRecommendBookTop(Integer size);

}
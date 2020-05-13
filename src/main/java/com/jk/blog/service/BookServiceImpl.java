package com.jk.blog.service;

import com.jk.blog.dao.BookRepository;
import com.jk.blog.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-05-12 14:40
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.getOne(bookId);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Page<Book> listBook(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> listBook(Long typeId, Pageable pageable) {
        return bookRepository.findByType(typeId, pageable);
    }

    @Override
    public List<Book> listRecommendBookTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return bookRepository.findTop(pageable);
    }
}
package com.jk.blog.service;

import com.jk.blog.dao.BookRepository;
import com.jk.blog.po.Blog;
import com.jk.blog.po.Book;
import com.jk.blog.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<Book> listBookByViews(Pageable pageable) {
        return bookRepository.findBookByViewQuery(pageable);
    }

    @Override
    public Page<Book> listBookByType(Type type, Pageable pageable) {
        return bookRepository.findAllByType(type, pageable);
    }

    @Override
    public List<Book> listRecommendBookTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return bookRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Book>> archiveBook() {
        List<String> years = bookRepository.findGroupYear();
        Map<String,List<Book>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,bookRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBook() {
        return bookRepository.count();
    }

    @Override
    public Page<Book> listBook(String query, Pageable pageable) {
        return bookRepository.findBookByQuery(query, pageable);
    }

}
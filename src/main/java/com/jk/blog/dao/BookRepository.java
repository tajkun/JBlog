package com.jk.blog.dao;

import com.jk.blog.po.Blog;
import com.jk.blog.po.Book;
import com.jk.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-05-12 14:10
 **/
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByTitle(String title);

    Page<Book> findAllByType(Type type, Pageable pageable);

    @Query("select b from Book b where b.recommend = true")
    List<Book> findTop(Pageable pageable);

}
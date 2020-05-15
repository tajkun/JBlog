package com.jk.blog.dao;

import com.jk.blog.po.Blog;
import com.jk.blog.po.Book;
import com.jk.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-05-12 14:10
 **/
public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Blog> {

    Book findByTitle(String title);

    Page<Book> findAllByType(Type type, Pageable pageable);

    @Query("select b from Book b where b.recommend = true")
    List<Book> findTop(Pageable pageable);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Book b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Book b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Book> findByYear(String year);

    @Query("select b from Book b where b.title like ?1")
    Page<Book> findBookByQuery(String query, Pageable pageable);

    @Query("select b from Book b ORDER BY b.views DESC")
    Page<Book> findBookByViewQuery(Pageable pageable);

}
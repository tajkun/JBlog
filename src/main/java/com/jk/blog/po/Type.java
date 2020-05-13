package com.jk.blog.po;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-11 16:55
 **/
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "分类名称不能为空")    //后端校验
    private String name;

//    @OneToMany(mappedBy = "type")
//    private List<Blog> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "type")
    private List<Book> books = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

}
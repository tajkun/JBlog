package com.jk.blog.vo;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-05-13 15:57
 **/
public class BookQuery {

    private String title;
    private Long typeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
package com.jk.blog.vo;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-13 18:42
 **/
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;

    public BlogQuery(String title) {
    }

    public BlogQuery() {
    }

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

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
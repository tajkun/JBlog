package com.jk.blog.service;

import com.jk.blog.po.User;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-11 21:42
 **/
public interface UserService {

    User checkUser(String username, String password);
}
package com.jk.blog.dao;

import com.jk.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-11 22:13
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
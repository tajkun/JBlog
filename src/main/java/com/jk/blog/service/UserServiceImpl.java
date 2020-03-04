package com.jk.blog.service;

import com.jk.blog.dao.UserRepository;
import com.jk.blog.po.User;
import com.jk.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-11 22:12
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
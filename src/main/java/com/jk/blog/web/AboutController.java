package com.jk.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-17 16:41
 **/
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about() {

        return "about";
    }
}
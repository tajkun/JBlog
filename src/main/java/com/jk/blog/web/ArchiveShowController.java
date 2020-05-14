package com.jk.blog.web;

import com.jk.blog.service.BlogService;
import com.jk.blog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-17 15:11
 **/
@Controller
public class ArchiveShowController {

    private final BlogService blogService;
    private final BookService bookService;

    @Autowired
    public ArchiveShowController(BookService bookService, BlogService blogService) {
        this.bookService = bookService;
        this.blogService = blogService;
    }

    @GetMapping("/archives")
    public String archives(Model model) {
//        model.addAttribute("archiveMap", blogService.archiveBlog());
//        model.addAttribute("blogCount",blogService.countBlog());
        model.addAttribute("archiveMap", bookService.archiveBook());
        model.addAttribute("bookCount", bookService.countBook());
        return "archives";
    }
}
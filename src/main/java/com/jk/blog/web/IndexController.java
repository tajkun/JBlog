package com.jk.blog.web;

import com.jk.blog.NotFoundException;
import com.jk.blog.service.BlogService;
import com.jk.blog.service.BookService;
import com.jk.blog.service.TagService;
import com.jk.blog.service.TypeService;
import com.jk.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-10 21:08
 **/
@Controller
public class IndexController {

    private final BlogService blogService;
    private final TypeService typeService;
    private final TagService tagService;
    private final BookService bookService;

    @Autowired
    public IndexController(BlogService blogService, TypeService typeService, TagService tagService, BookService bookService) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("bookPage", bookService.listBook(pageable));
        model.addAttribute("type1",typeService.listTypeTop(7));
        model.addAttribute("type2", typeService.listTypeBetween(8L, 15L));
        model.addAttribute("type3", typeService.listTypeBetween(16L, 22L));
        model.addAttribute("type4", typeService.listTypeBetween(23L, 28L));
        model.addAttribute("type5", typeService.listTypeBetween(29L, 33L));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBooks", bookService.listRecommendBookTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog",blogService.getAndConvertBlog(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }
}
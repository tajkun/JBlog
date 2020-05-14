package com.jk.blog.web;

import com.jk.blog.NotFoundException;
import com.jk.blog.po.Book;
import com.jk.blog.po.Type;
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

import java.util.ArrayList;
import java.util.List;

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
        // 经部
        List<Type> types1 = typeService.listTypeTop(7);
        List<Book> books1 = new ArrayList<>();
        types1.forEach(type1 -> books1.addAll(type1.getBooks()));
        model.addAttribute("type1", types1);
        model.addAttribute("books1", books1);
        // 史部
        List<Type> types2 = typeService.listTypeBetween(8L, 15L);
        List<Book> books2 = new ArrayList<>();
        types2.forEach(type2 -> books2.addAll(type2.getBooks()));
        model.addAttribute("type2", types2);
        model.addAttribute("books2", books2);
        // 子部
        List<Type> types3 = typeService.listTypeBetween(16L, 22L);
        List<Book> books3 = new ArrayList<>();
        types3.forEach(type3 -> books3.addAll(type3.getBooks()));
        model.addAttribute("type3", types3);
        model.addAttribute("books3", books3);
        // 集部
        List<Type> types4 = typeService.listTypeBetween(23L, 28L);
        List<Book> books4 = new ArrayList<>();
        types4.forEach(type4 -> books4.addAll(type4.getBooks()));
        model.addAttribute("type4", types4);
        model.addAttribute("books4", books4);
        // 书部
        List<Type> types5 = typeService.listTypeBetween(29L, 33L);
        List<Book> books5 = new ArrayList<>();
        types5.forEach(type5 -> books5.addAll(type5.getBooks()));
        model.addAttribute("type5", types5);
        model.addAttribute("books5", books5);

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

    @GetMapping("/book/{id}")
    public String book(@PathVariable Long id,Model model) {
        Book book = bookService.getBookById(id);
//        String description = toHTMLString(book.getDescription());
//        book.setDescription(description);
        model.addAttribute("book", book);
        model.addAttribute("articles", blogService.listBlogByBook(book));
        return "book";
    }


}
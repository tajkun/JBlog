package com.jk.blog.web;

import com.jk.blog.po.Book;
import com.jk.blog.po.Type;
import com.jk.blog.service.BlogService;
import com.jk.blog.service.BookService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-17 11:55
 **/
@Controller
public class TypeShowController {

    private final TypeService typeService;
    private final BlogService blogService;
    private final BookService bookService;

    @Autowired
    public TypeShowController(TypeService typeService, BlogService blogService, BookService bookService) {
        this.typeService = typeService;
        this.blogService = blogService;
        this.bookService = bookService;
    }

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable String id, Model model) {
        Long longId = Long.parseLong(id);
        List<Type> types = typeService.listTypeTop(10000);
        String typeName = null;
        if (longId == -1) {
            typeName = "阅读排行";
            model.addAttribute("typeStr", typeName);
            model.addAttribute("types",types);
            model.addAttribute("bookPage", bookService.listBookByViews(pageable));
            model.addAttribute("typeId", longId);
            this.typeRelative(model);
            return "types";
        } else {
            Type type = typeService.getType(longId);
            typeName = type.getName();
            model.addAttribute("typeStr", typeName);
            model.addAttribute("types", types);
            model.addAttribute("bookPage", bookService.listBookByType(type, pageable));
            model.addAttribute("typeId", longId);
            this.typeRelative(model);
            return "types";
        }
    }

    private void typeRelative(Model model) {
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
    }

}
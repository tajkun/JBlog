package com.jk.blog.web.admin;

import com.jk.blog.po.User;
import com.jk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-11 22:23
 **/
@Controller
@RequestMapping("/admin")    //访问根路径
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        System.out.println("访问login");
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username,password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            System.out.println(user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
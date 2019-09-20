package com.example.tmall.controller;

import com.example.tmall.pojo.User;
import com.example.tmall.service.UserService;
import com.example.tmall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("admin_user_list")
    public String list(Page page, Model model){
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> us = userService.list();
        int total = (int)new PageInfo<>(us).getTotal();
        page.setTotal(total);

        model.addAttribute("us", us);
        model.addAttribute("page", page);

        return "admin/listUser";
    }
}

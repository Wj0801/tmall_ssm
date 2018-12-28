package com.wj.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.tmall.pojo.User;
import com.wj.tmall.service.UserService;
import com.wj.tmall.util.Page;
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

    /*
    1. 获取分页对象
    2. 设置分页信息
    3. 查询用户集合
    4. 通过PageInfo获取总数，并设置在page对象上
    5. 把用户集合设置到model的"users"属性上
    6. 把分页对象设置到model的"page"属性上
    5. 服务端跳转到admin/listUser.jsp页面
    6. 在listUser.jsp用c:forEach遍历"users"集合
     */

        @RequestMapping("admin_user_list")
        public String list(Model model, Page page) {
            PageHelper.offsetPage(page.getStart(), page.getCount());

            List<User> us = userService.list();

            int total = (int) new PageInfo<>(us).getTotal();
            page.setTotal(total);

            model.addAttribute("us", us);
            model.addAttribute("page", page);

            return "admin/listUser";
        }

    }
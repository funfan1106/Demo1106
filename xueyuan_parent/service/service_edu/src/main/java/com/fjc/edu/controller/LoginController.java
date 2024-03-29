package com.fjc.edu.controller;

import com.fjc.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @program: guli_parent
 * @description: 登录
 * @author: Mr.Fan
 * @create: 2021-02-06 17:00
 **/

@RestController
@RequestMapping("eduservice/user")
@CrossOrigin //解决跨域问题
public class LoginController {
    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}

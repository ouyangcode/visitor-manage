package com.fline.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "跳转接口")
@Controller
public class JumpController {
    @Reference
    private com.fline.service.userService userService;

    @Reference
    private com.fline.service.lfdjbService lfdjbService;

    @ApiOperation(value = "首页")
    @GetMapping("/")
    public String Shouye(){
            return "login";
    }

    @ApiOperation(value = "跳转目标地址")
    @GetMapping("/Jumpto")
    public String Jumpto(@RequestParam("url") String url){
        return url;
    }

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public String login(@RequestParam String password, @RequestParam String username, Model model) {
           if (username.equals("admin")&&password.equals("admin")){
               return "views/index";
           }
           else if(username.equals("zhibao")&&password.equals("zhibao")){
               return "views/zhibao";
           }
           else {
               model.addAttribute("error", "用户名或密码错误！");
               return "login";
           }
    }

    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}

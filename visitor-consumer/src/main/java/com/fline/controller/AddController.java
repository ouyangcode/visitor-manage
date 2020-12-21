package com.fline.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fline.model.User;
import com.fline.model.Visitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "新增接口")
@RestController
public class AddController {
    @Reference
    private com.fline.service.userService userService;
    @Reference
    private com.fline.service.lfdjbService lfdjbService;

    @ApiOperation(value = "新增用户")
    @PostMapping("addUser")
    public boolean addUser(User user){
        if (userService.insUser(user)!=0){
            return true;
        }
        return false;
    }

    @ApiOperation(value = "新增来访登记表")
    @PostMapping("addLfdjb")
    public boolean addLfdjb(Visitor visitor){
        if (lfdjbService.insLfdjb(visitor)!=0)
            return true;
        return false;
    }
}

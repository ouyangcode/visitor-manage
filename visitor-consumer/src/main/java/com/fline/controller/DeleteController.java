package com.fline.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "删除接口")
@RestController
public class DeleteController {
    @Reference
    private com.fline.service.userService userService;

    @Reference
    private com.fline.service.lfdjbService lfdjbService;

    @ApiOperation(value = "删除用户")
    @PostMapping("delUser")
    public boolean delUser(int id){
        if (userService.delUser(id)!=0) {
            return true;
        }
        return false;
    }

    @ApiOperation(value = "删除来访登记表")
    @PostMapping("delLfdjb")
    public boolean delLfdjb(int id){
        if (lfdjbService.delLfdjb(id)!=0) {
            return true;
        }
        return false;
    }
}

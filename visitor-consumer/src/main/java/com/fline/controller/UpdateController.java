package com.fline.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fline.model.User;
import com.fline.model.Visitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "更新接口")
@RestController
public class UpdateController {

    @Reference
    private com.fline.service.userService userService;

    @Reference
    private com.fline.service.lfdjbService lfdjbService;

    @ApiOperation(value = "更新用户")
    @PostMapping("upUser")
    public boolean upUser(User user) {
        if (userService.upUser(user) != 0)
            return true;
        return false;
    }

    @ApiOperation(value = "更新来访登记表")
    @PostMapping("upLfdjb")
    public boolean upLfdjb(Visitor visitor) {
        if (lfdjbService.upLfdjb(visitor) != 0) {
            return true;
        }
        return false;
    }

    @ApiOperation(value = "更具具体字段更新来访登记表")
    @PostMapping("auplfdjb")
    public boolean auplfdjb(
            @RequestParam Integer id,
            @RequestParam String field,
            @RequestParam String updateValue
    ) {
        Visitor visitor = lfdjbService.getLfdjbById(id);
        if (visitor == null)
            return false;
        if (field.equals("djy")) {
            visitor.setDjy(updateValue);
        }
        if (field.equals("khdw")) {
            visitor.setKhdw(updateValue);
        }
        if (field.equals("cph")) {
            visitor.setCph(updateValue);
        }
        if (field.equals("kkhxm")) {
            visitor.setKhxm(updateValue);
        }
        if (field.equals("rcsy")) {
            visitor.setRcsy(updateValue);
        }
        if (field.equals("aqcs")) {
            visitor.setAqcs(updateValue);
        }
        if (field.equals("zkwp")) {
            visitor.setZkwp(updateValue);
        }
        if (field.equals("cwgbh")) {
            visitor.setCwgbh(updateValue);
        }
        if (field.equals("sjh")) {
            visitor.setSjh(updateValue);
        }
        if (field.equals("sfqylf")) {
            visitor.setSfqylf(Integer.valueOf(updateValue));
        }
        if (lfdjbService.upLfdjb(visitor) != 0) {
            return true;
        }
        return false;
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("aupuser")
    public boolean aupnbuser(
            @RequestParam Integer id,
            @RequestParam String field,
            @RequestParam String updateValue
    ) {
        User user = userService.getUserById(id);
        if (user == null)
            return false;
        if (field.equals("realname")) {
            user.setRealname(updateValue);
        }

        if (userService.upUser(user) != 0)
            return true;
        return false;
    }

    @ApiOperation(value = "来访登记表rc")
    @PostMapping("lfdjbrc")
    public boolean lfdjbrc(@RequestParam Integer id) {
        Visitor visitor = lfdjbService.getLfdjbById(id);
        if (visitor == null)
            return false;
        else {
            if (visitor.getJcsj() != null)
                return false;
            Date date = new Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            visitor.setJcsj(sim.format(date));
            if (lfdjbService.upLfdjb(visitor) != 0) {
                return true;
            }
            return false;
        }
    }

    @ApiOperation(value = "来访登记表cc")
    @PostMapping("lfdjbcc")
    public boolean lfdjbcc(@RequestParam Integer id) {
        Visitor visitor = lfdjbService.getLfdjbById(id);
        if (visitor == null) {
            return false;
        } else {
            if (visitor.getJcsj() == null) {
                return false;
            }
            Date date = new Date();
            long rightTime = (long) (date.getTime() + 8 * 60 * 60 * 1000); //把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
            date = new Date(rightTime);
            visitor.setCcsj(date);
            visitor.setSfjslc(1);
            if (lfdjbService.upLfdjb(visitor) != 0) {
                return true;
            }
            return false;
        }
    }
}

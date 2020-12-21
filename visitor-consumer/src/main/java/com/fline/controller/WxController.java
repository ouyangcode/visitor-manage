package com.fline.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fline.model.User;
import com.fline.model.Visitor;
import com.fline.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Map;

@Api(tags = "微信接口")
@RestController
public class WxController {

    @Reference
    private com.fline.service.userService userService;

    @Reference
    private com.fline.service.lfdjbService lfdjbService;

    @Reference
    private CommonService commonService;

    @ApiOperation(value = "代码")
    @GetMapping("/Code")
    public String Code(@RequestParam String code, @RequestParam String state, @RequestParam String appid) {
//        utilpack.sendGet("?access_token=ACCESS_TOKEN&code=CODE",)
        return "Code = " + code + "   state = " + state;

//        return new CommonResult(code,state);
    }

    /*
        /*
        * 客户端小程序登录接口
        * */
    @ApiOperation(value = "微信登陆")
    @GetMapping("/vxlogin")
    public Map<String, Object> vxlogin(
            @RequestParam("code") String code
    ) {
        List<Map<String, Object>> list = new ArrayList<>();
        Utilpack utilpackd = new Utilpack();
        String appid = "wx9280cb3270ea91c8";
        String key = "9e083036d9faed8d50fdaac67f2fc4ce";
        String openid = utilpackd.getopenid(appid, key, code);
        User user = userService.getUserByOpenId(openid);
        Map map = new HashMap();
        if (user == null) {
            user = new User();
            user.setCode(0);
            user.setOpenid(openid);
            user.setZcsj(new Date());
            user.setRealname("来访客人");
            if (userService.insUser(user) != 0) {
                user = userService.getUserByOpenId(openid);
                map.put("code", 0);
                map.put("msg", "successful");
                map.put("userinfo", user);
                return map;
            }
            map.put("code", -1);
            map.put("msg", "insert data error");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "successful");
        map.put("userinfo", user);
        return map;
    }

    @ApiOperation(value = "获取token")
    @GetMapping("/gettoken")
    public String gettoken() {
        return commonService.GetToken();
    }

    @Deprecated
    @ApiOperation(value = "企业微信登录")
    @GetMapping("/qyvxlogin")
    public Map<String, Object> qyvxlogin(@RequestParam String code) {
        Utilpack utilpack = new Utilpack();
        Map map = new HashMap();
        String access_token = commonService.GetToken();
        if (access_token.length() == 4) {
            access_token = utilpack.getAccess_token();
            commonService.SetToken(access_token);
        }
        if (access_token.equals("error")) {
            map.put("code", 2);
            map.put("msg", "服务器阻塞");
            return map;
        }
        String userid = utilpack.getuserid(access_token, code);
        if (userid.equals("codeerror")) {
            access_token = utilpack.getAccess_token();
            commonService.SetToken(access_token);
            userid = utilpack.getuserid(access_token, code);
            if (userid == null) {
                map.put("code", 2);
                map.put("msg", "微信服务错误");
                return map;
            }
        }
        User user = userService.getUserByOpenId(userid);
        if (user == null) {
            User nuser = new User();
            nuser.setCode(1);
            nuser.setOpenid(userid);
            if (userService.insUser(nuser) != 0) {
                user = userService.getUserByOpenId(userid);
                map.put("code", 0);
                map.put("msg", "successful");
                map.put("user", user);
                return map;
            } else {
                map.put("code", 1);
                map.put("msg", "创建用户失败");
                return map;
            }
        }
        map.put("code", 0);
        map.put("msg", "successful");
        map.put("user", user);
        return map;
    }



    @ApiOperation(value = "获取最新")
    @GetMapping("/getNewest")
    public Map<String, Object> getNewest(@RequestParam Integer lfrywxid) {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d E");
        List<Map<String, Object>> list = new ArrayList<>();
        List<Visitor> visitors = lfdjbService.getLfdjbList();
        List<Map> logs = new ArrayList<>();
        Map map = new HashMap();
        if (visitors != null) {
            for (Visitor visitor : visitors) {
                if (visitor.getLfryvxid() == lfrywxid) {
                    Map items = new HashMap();
                    items.put("lfrq", sdf.format(visitor.getLfrq()));
                    items.put("jqbm", visitor.getJqbm());
                    items.put("jqr", visitor.getJqr());
                    items.put("sfqylf", visitor.getSfqylf());
                    logs.add(items);
                }
            }
        }
        if (logs.size() > 0) {
            map.put("code", 0);
            map.put("msg", "successful");
            map.put("newest", logs.get(0));
        } else {
            map.put("code", 0);
            map.put("msg", "successful");
            map.put("newest", null);
        }
        return map;
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/upuserinfo")
    public Map<String, Object> upuserinfo(@RequestParam String realname,
                                          @RequestParam Integer id) throws ParseException {
        List<User> users = userService.getUserList();
        Map map = new HashMap();
        Integer qrrvxid = 0;
        User qyyhuser = new User();
        if (users != null) {
            for (User user : users) {
                if (user.getId() == id) {
                    qyyhuser = user;
                    break;
                }
            }
        }
        if (qyyhuser != null) {
            qyyhuser.setZcsj(new Date());
            qyyhuser.setRealname(realname);
            if (userService.upUser(qyyhuser) != 0) {
                map.put("code", 0);
                map.put("msg", "successful");
                map.put("result", true);
            } else {
                map.put("code", 1);
                map.put("msg", "保存失败");
                map.put("result", false);
            }
        } else {
            map.put("code", 2);
            map.put("msg", "请根据正常程序进行登录");
            map.put("result", false);
        }
        return map;
    }

    @ApiOperation(value = "插入来访登记表")
    @PostMapping("/inslfdjb")
    public Map<String, Object> inslfdjb(
            @RequestParam String cph,
            @RequestParam String jqbm,
            @RequestParam String jqr,
            @RequestParam String khdw,
            @RequestParam String khxm,
            @RequestParam String idcard,
            @RequestParam String lfrq,
            @RequestParam String rcsy,
            @RequestParam String sjh,
            @RequestParam Integer uid) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<User> users = userService.getUserList();
        Map map = new HashMap();
        Integer qrrvxid = 0;
        User qrruser = null;
        if (users != null) {
            for (User user : users) {
                if ((user.getRealname() == null) ? false : user.getRealname().equals(jqbm + "-" + jqr)) {
                    qrruser = user;
                    break;
                }
            }
        }
        if (qrruser != null)
            qrrvxid = qrruser.getId();
        if (qrrvxid != 0 || 1 == 1) {
            Date date = simpleDateFormat.parse(lfrq);
            long rightTime = (long) (date.getTime() + 8 * 60 * 60 * 1000); //把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
            date = new Date(rightTime);
            List<Map<String, Object>> list = new ArrayList<>();
            Visitor visitor = new Visitor();
            visitor.setLfryvxid(uid);
            visitor.setCph(cph);
            visitor.setJqbm(jqbm);
            visitor.setJqr(jqr);
            visitor.setKhdw(khdw);
            visitor.setKhxm(khxm);
            visitor.setLfrq(date);
            visitor.setSfjslc(0);
            visitor.setSfqylf(0);
            visitor.setIdcard(idcard);
            visitor.setRcsy(rcsy);
            visitor.setSjh(sjh);
            visitor.setQrrvxid(qrrvxid);
            if (lfdjbService.insLfdjb(visitor) != 0 || 1 == 1) {
                int i;
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();
                Map sendmsgMap = new HashMap();
                Map miniprogram_notice = new HashMap();
                List content_item = new ArrayList();
                for (i = 0; i < 4; i++) {
                    Map itemMap = new HashMap();
                    if (i == 0) {
                        itemMap.put("key", "访客姓名");
                        itemMap.put("value", khxm);
                    }
                    if (i == 1) {
                        itemMap.put("key", "客户单位");
                        itemMap.put("value", khdw);
                    }
                    if (i == 2) {
                        itemMap.put("key", "来访事由");
                        itemMap.put("value", rcsy);
                    }
                    if (i == 3) {
                        itemMap.put("key", "来访日期");
                        itemMap.put("value", lfrq);
                    }
                    content_item.add(itemMap);
                }
                miniprogram_notice.put("appid", "wx9280cb3270ea91c8");
                miniprogram_notice.put("page", "pages/fksp/fksp");
                miniprogram_notice.put("title", "您有新的访客啦，点击处理");
                miniprogram_notice.put("description", sim.format(now));
                miniprogram_notice.put("emphasis_first_item", true);
                miniprogram_notice.put("content_item", content_item);
                sendmsgMap.put("touser", null);
                sendmsgMap.put("msgtype", "miniprogram_notice");
                sendmsgMap.put("miniprogram_notice", miniprogram_notice);
                Utilpack utilpack = new Utilpack();
                utilpack.QYsendMessageToUser(utilpack.getAccess_token(), sendmsgMap);
                map.put("code", 0);
                map.put("msg", "successful");
                map.put("result", true);
            } else {
                map.put("code", 1);
                map.put("msg", "保存失败");
                map.put("result", false);
            }
        } else {
            map.put("code", 2);
            map.put("msg", "接洽人未找到");
            map.put("result", false);
        }
        return map;
    }

    /**
     * 
     * @param lfrywxid
     * @// TODO: 2020/12/18 部门数据需要对接真实的账号体系
     * @return
     */
    @ApiOperation(value = "获取部门数据")
    @GetMapping("/getbmarray")
    public String[] bmarray(@RequestParam Integer lfrywxid) {
        String bmarray[] = {
                "智能精益项目部",
                "人力资源部",
                "综合管理部",
                "维修车间",
                "油品车间",
                "生产部",
                "设备部",
                "重整车间",
                "治安保卫部",
                "质检计量部",
                "企业发展部",
                "PTA项目部",
                "技术部",
                "采购管理部",
                "焦化硫磺车间",
                "渣油脱蜡车间",
                "财务部",
                "动力车间",
                "二期项目部",
                "芳烃车间",
                "HES管理部",
                "加裂车间",
                "海旺运营部",
                "精制车间",
                "客户服务部"
        };
        return bmarray;
    }


    @ApiOperation(value = "微信用户信息获取")
    @GetMapping("/wxgetuserinfo")
    public Map wxgetuserinfo(@RequestParam String wxuid) {
        Map resultmap = new HashMap();
        User user = userService.getUserByOpenId(wxuid);
        if (user == null) {
            resultmap.put("code", 1);
            resultmap.put("msg", "账号未注册");
            return resultmap;
        }
        resultmap.put("code", 0);
        resultmap.put("msg", "请求成功");
        resultmap.put("userinfo", user);
        return resultmap;
    }

    @ApiOperation(value = "微信用户记录")
    @GetMapping("/wxinsuser")
    public Map wxinsuser(@RequestParam String openid, @RequestParam String realname) {
        Map resultmap = new HashMap();
        User user = new User();
        user.setCode(1);
        user.setOpenid(openid);
        user.setRealname(realname);
        Date date = new Date();
        user.setZcsj(date);
        if (userService.insUser(user) != 0) {
            User getuser = userService.getUserByOpenId(openid);
            if (getuser != null) {
                resultmap.put("code", 0);
                resultmap.put("msg", "添加成功");
                resultmap.put("userinfo", getuser);
                return resultmap;
            }
            resultmap.put("code", 1);
            resultmap.put("msg", "返回用户信息失败");
            return resultmap;
        }
        resultmap.put("code", 2);
        resultmap.put("msg", "添加失败");
        return resultmap;
    }

    @ApiOperation(value = "获取用户日志")
    @GetMapping("/getUserlogs")
    public Map<String, Object> getUserlogs(@RequestParam Integer lfrywxid) {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d E");
        List<Map<String, Object>> list = new ArrayList<>();
        List<Visitor> visitors = lfdjbService.getLfdjbList();
        List<Map> logs = new ArrayList<>();
        Map map = new HashMap();
        if (visitors != null) {
            for (Visitor visitor : visitors) {
                if (visitor.getLfryvxid() == lfrywxid) {
                    Map items = new HashMap();
                    items.put("lfrq", sdf.format(visitor.getLfrq()));
                    items.put("jqbm", visitor.getJqbm());
                    items.put("jqr", visitor.getJqr());
                    items.put("sfqylf", visitor.getSfqylf());
                    logs.add(items);
                }
            }
        }
        map.put("code", 0);
        map.put("msg", "successful");
        map.put("lfdjbs", logs);
        return map;
    }

    @ApiOperation(value = "审批")
    @GetMapping("/sp")
    public Map<String, Object> sp(@RequestParam boolean total, @RequestParam Integer id) {
        Visitor visitor = lfdjbService.getLfdjbById(id);
        Map map = new HashMap();
        if (visitor == null) {
            map.put("code", 1);
            map.put("msg", "未查询到该来访记录！");
        }
        if (total) {
            visitor.setSfqylf(1);
        } else {
            visitor.setSfqylf(2);
            visitor.setSfjslc(1);
        }
        lfdjbService.upLfdjb(visitor);
        map.put("code", 0);
        map.put("msg", "successful");
        return map;
    }

    @ApiOperation(value = "获取审批记录")
    @GetMapping(value = "/getSplist")
    public Map<String, Object> getSplist(@RequestParam Integer qrrvxid) {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d E");
        Date date = new Date();
        List<Map<String, Object>> list = new ArrayList<>();
        List<Visitor> visitors = lfdjbService.getLfdjbList();
        List<Map> logs = new ArrayList<>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        Map map = new HashMap();
        if (visitors != null) {
            for (Visitor visitor : visitors) {
                if (visitor.getQrrvxid() == qrrvxid && visitor.getSfjslc() == 0 && visitor.getSfqylf() == 0 && String.valueOf(sim.format(visitor.getLfrq())).equals(sim.format(date))) {
                    Map items = new HashMap();
                    String khxm = "来访客户";
                    if (visitor.getKhdw() != null && !visitor.getKhdw().equals("")) {
                        khxm = visitor.getKhdw() + "--" + visitor.getKhxm();
                    } else
                        khxm = visitor.getKhxm();
                    items.put("id", visitor.getId());
                    items.put("khxm", khxm);
                    items.put("lfrq", sdf.format(visitor.getLfrq()));
                    items.put("rcsy", visitor.getRcsy());
                    logs.add(items);
                }
            }
        }
        map.put("code", 0);
        map.put("msg", "successful");
        map.put("splist", logs);
        return map;
    }

}
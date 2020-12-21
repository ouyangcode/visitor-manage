package com.fline.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fline.model.User;
import com.fline.model.Visitor;
import com.fline.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "查询接口")
@RestController
public class SelectController {

    @Reference
    private com.fline.service.userService userService;

    @Reference
    private com.fline.service.lfdjbService lfdjbService;

    @Reference
    private CommonService commonService;

    @ApiOperation(value = "查询用户列表")
    @GetMapping("getuserList")
    public Map<String, Object> getUserList() {
        List<User> users = userService.getUserList();
        List<Map<String, Object>> list = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                Integer id = user.getId();
                Integer code = user.getCode();
                String openid = user.getOpenid();
                String realname = user.getRealname();
                Date zcsj = user.getZcsj();
                Map map = new HashMap();
                map.put("id", id);
                map.put("code", code);
                map.put("openid", openid);
                map.put("realname", realname);
                map.put("zcsj", zcsj);
                list.add(map);
            }
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        map1.put("code", 0);
        map1.put("msg", "ok");
        map1.put("data", list);
        return map1;
    }

    @ApiOperation(value = "查询来访登记列表")
    @GetMapping("getlfdjbList")
    public Map<String, Object> getlfdjbList(
            @RequestParam(required = false) String khname,
            @RequestParam(required = false) String datelimit,
            @RequestParam(required = false) String inidcard) throws ParseException {
        List<Visitor> visitors = lfdjbService.getLfdjbList();
        List<Map<String, Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date();
        String starttime;
        String endtime;
        if (datelimit != null && !datelimit.equals("")) {
            starttime = datelimit.split(" - ")[0].trim();
            endtime = datelimit.split(" - ")[1].trim();
            date1 = sdf.parse(starttime + " 00:00:00");
            date2 = sdf.parse(endtime + " 23:59:59");
        }
        if (visitors != null) {
            if (khname == null || khname.equals(""))
                if (datelimit != null && !datelimit.equals(""))
                    if (inidcard != null && !inidcard.equals(""))
                        for (Visitor visitor : visitors) {
                            Integer id = visitor.getId();
                            String aqcs = visitor.getAqcs();
                            Date ccsj = visitor.getCcsj();
                            String cph = visitor.getCph();
                            String cwgbh = visitor.getCwgbh();
                            String djy = visitor.getDjy();
                            String jcsj = visitor.getJcsj();
                            String jqbm = visitor.getJqbm();
                            String jqr = visitor.getJqr();
                            String khdw = visitor.getKhdw();
                            String kkhxm = visitor.getKhxm();
                            Date lfrq = visitor.getLfrq();
                            if (lfrq.getTime() < date1.getTime() || lfrq.getTime() > date2.getTime()) {
                                continue;
                            }
                            Integer lfryvxid = visitor.getLfryvxid();
                            String rcsy = visitor.getRcsy();
                            String idcard = visitor.getIdcard();
                            if (!inidcard.equals(idcard)) {
                                continue;
                            }
                            Integer sfjslc = visitor.getSfjslc();
                            String sjh = visitor.getSjh();
                            String zkwp = visitor.getZkwp();
                            Integer sfqylf = visitor.getSfqylf();
                            Integer qrrvxid = visitor.getQrrvxid();
                            String sfqylfstr = "未知";
                            String sfjslcstr = "未知";
                            if (sfqylf == 0) {
                                sfqylfstr = "未确认";
                            }
                            if (sfqylf == 1) {
                                sfqylfstr = "已确认";
                            }
                            if (sfqylf == 2) {
                                sfqylfstr = "已拒绝";
                            }
                            if (sfjslc == 0) {
                                sfjslcstr = "未结束";
                            }
                            if (sfjslc == 1) {
                                sfjslcstr = "已结束";
                            }
                            Map map = new HashMap();
                            map.put("id", id);
                            map.put("aqcs", aqcs);
                            if (ccsj != null)
                                map.put("ccsj", sdf.format(ccsj));
                            map.put("cph", cph);
                            map.put("cwgbh", cwgbh);
                            map.put("djy", djy);
                            map.put("jcsj", jcsj);
                            map.put("jqbm", jqbm);
                            map.put("jqr", jqr);
                            map.put("khdw", khdw);
                            map.put("kkhxm", kkhxm);
                            if (lfrq != null)
                                map.put("lfrq", sdf2.format(lfrq));
                            map.put("lfryvxid", lfryvxid);
                            map.put("rcsy", rcsy);
                            map.put("sfjslc", sfjslcstr);
                            map.put("sjh", sjh);
                            map.put("zkwp", zkwp);
                            map.put("sfqylf", sfqylfstr);
                            map.put("qrrvxid", qrrvxid);
                            map.put("idcard", idcard);
                            list.add(map);
                        }
                    else
                        for (Visitor visitor : visitors) {
                            Integer id = visitor.getId();
                            String aqcs = visitor.getAqcs();
                            Date ccsj = visitor.getCcsj();
                            String cph = visitor.getCph();
                            String cwgbh = visitor.getCwgbh();
                            String djy = visitor.getDjy();
                            String jcsj = visitor.getJcsj();
                            String jqbm = visitor.getJqbm();
                            String jqr = visitor.getJqr();
                            String khdw = visitor.getKhdw();
                            String kkhxm = visitor.getKhxm();
                            Date lfrq = visitor.getLfrq();
                            if (lfrq.getTime() < date1.getTime() || lfrq.getTime() > date2.getTime()) {
                                continue;
                            }
                            Integer lfryvxid = visitor.getLfryvxid();
                            String rcsy = visitor.getRcsy();
                            String idcard = visitor.getIdcard();
                            Integer sfjslc = visitor.getSfjslc();
                            String sjh = visitor.getSjh();
                            String zkwp = visitor.getZkwp();
                            Integer sfqylf = visitor.getSfqylf();
                            Integer qrrvxid = visitor.getQrrvxid();
                            String sfqylfstr = "未知";
                            String sfjslcstr = "未知";
                            if (sfqylf == 0) {
                                sfqylfstr = "未确认";
                            }
                            if (sfqylf == 1) {
                                sfqylfstr = "已确认";
                            }
                            if (sfqylf == 2) {
                                sfqylfstr = "已拒绝";
                            }
                            if (sfjslc == 0) {
                                sfjslcstr = "未结束";
                            }
                            if (sfjslc == 1) {
                                sfjslcstr = "已结束";
                            }
                            Map map = new HashMap();
                            map.put("id", id);
                            map.put("aqcs", aqcs);
                            map.put("cph", cph);
                            map.put("cwgbh", cwgbh);
                            map.put("djy", djy);
                            map.put("jcsj", jcsj);
                            map.put("jqbm", jqbm);
                            map.put("jqr", jqr);
                            map.put("khdw", khdw);
                            map.put("kkhxm", kkhxm);
                            if (ccsj != null)
                                map.put("ccsj", sdf.format(ccsj));
                            if (lfrq != null)
                                map.put("lfrq", sdf2.format(lfrq));
                            map.put("lfryvxid", lfryvxid);
                            map.put("rcsy", rcsy);
                            map.put("sfjslc", sfjslcstr);
                            map.put("sjh", sjh);
                            map.put("zkwp", zkwp);
                            map.put("sfqylf", sfqylfstr);
                            map.put("qrrvxid", qrrvxid);
                            map.put("idcard", idcard);
                            list.add(map);
                        }
                else if (inidcard != null && !inidcard.equals(""))
                    for (Visitor visitor : visitors) {
                        Integer id = visitor.getId();
                        String aqcs = visitor.getAqcs();
                        Date ccsj = visitor.getCcsj();
                        String cph = visitor.getCph();
                        String cwgbh = visitor.getCwgbh();
                        String djy = visitor.getDjy();
                        String jcsj = visitor.getJcsj();
                        String jqbm = visitor.getJqbm();
                        String jqr = visitor.getJqr();
                        String khdw = visitor.getKhdw();
                        String kkhxm = visitor.getKhxm();
                        Date lfrq = visitor.getLfrq();
                        Integer lfryvxid = visitor.getLfryvxid();
                        String rcsy = visitor.getRcsy();
                        String idcard = visitor.getIdcard();
                        if (!inidcard.equals(idcard))
                            continue;
                        Integer sfjslc = visitor.getSfjslc();
                        String sjh = visitor.getSjh();
                        String zkwp = visitor.getZkwp();
                        Integer sfqylf = visitor.getSfqylf();
                        Integer qrrvxid = visitor.getQrrvxid();
                        String sfqylfstr = "未知";
                        String sfjslcstr = "未知";
                        if (sfqylf == 0) {
                            sfqylfstr = "未确认";
                        }
                        if (sfqylf == 1) {
                            sfqylfstr = "已确认";
                        }
                        if (sfqylf == 2) {
                            sfqylfstr = "已拒绝";
                        }
                        if (sfjslc == 0) {
                            sfjslcstr = "未结束";
                        }
                        if (sfjslc == 1) {
                            sfjslcstr = "已结束";
                        }
                        Map map = new HashMap();
                        map.put("id", id);
                        map.put("aqcs", aqcs);
                        if (ccsj != null)
                            map.put("ccsj", sdf.format(ccsj));
                        if (lfrq != null)
                            map.put("lfrq", sdf2.format(lfrq));
                        map.put("cph", cph);
                        map.put("cwgbh", cwgbh);
                        map.put("djy", djy);
                        map.put("jcsj", jcsj);
                        map.put("jqbm", jqbm);
                        map.put("jqr", jqr);
                        map.put("khdw", khdw);
                        map.put("kkhxm", kkhxm);
                        map.put("lfryvxid", lfryvxid);
                        map.put("rcsy", rcsy);
                        map.put("sfjslc", sfjslcstr);
                        map.put("sjh", sjh);
                        map.put("zkwp", zkwp);
                        map.put("sfqylf", sfqylfstr);
                        map.put("qrrvxid", qrrvxid);
                        map.put("idcard", idcard);
                        list.add(map);
                    }
                else
                    for (Visitor visitor : visitors) {
                        Integer id = visitor.getId();
                        String aqcs = visitor.getAqcs();
                        Date ccsj = visitor.getCcsj();
                        String cph = visitor.getCph();
                        String cwgbh = visitor.getCwgbh();
                        String djy = visitor.getDjy();
                        String jcsj = visitor.getJcsj();
                        String jqbm = visitor.getJqbm();
                        String jqr = visitor.getJqr();
                        String khdw = visitor.getKhdw();
                        String kkhxm = visitor.getKhxm();
                        Date lfrq = visitor.getLfrq();
                        Integer lfryvxid = visitor.getLfryvxid();
                        String rcsy = visitor.getRcsy();
                        String idcard = visitor.getIdcard();
                        Integer sfjslc = visitor.getSfjslc();
                        String sjh = visitor.getSjh();
                        String zkwp = visitor.getZkwp();
                        Integer sfqylf = visitor.getSfqylf();
                        Integer qrrvxid = visitor.getQrrvxid();
                        String sfqylfstr = "未知";
                        String sfjslcstr = "未知";
                        if (sfqylf == 0) {
                            sfqylfstr = "未确认";
                        }
                        if (sfqylf == 1) {
                            sfqylfstr = "已确认";
                        }
                        if (sfqylf == 2) {
                            sfqylfstr = "已拒绝";
                        }
                        if (sfjslc == 0) {
                            sfjslcstr = "未结束";
                        }
                        if (sfjslc == 1) {
                            sfjslcstr = "已结束";
                        }
                        Map map = new HashMap();
                        map.put("id", id);
                        map.put("aqcs", aqcs);
                        if (ccsj != null)
                            map.put("ccsj", sdf.format(ccsj));
                        if (lfrq != null)
                            map.put("lfrq", sdf2.format(lfrq));
                        map.put("cph", cph);
                        map.put("cwgbh", cwgbh);
                        map.put("djy", djy);
                        map.put("jcsj", jcsj);
                        map.put("jqbm", jqbm);
                        map.put("jqr", jqr);
                        map.put("khdw", khdw);
                        map.put("kkhxm", kkhxm);
                        map.put("lfryvxid", lfryvxid);
                        map.put("rcsy", rcsy);
                        map.put("sfjslc", sfjslcstr);
                        map.put("sjh", sjh);
                        map.put("zkwp", zkwp);
                        map.put("sfqylf", sfqylfstr);
                        map.put("qrrvxid", qrrvxid);
                        map.put("idcard", idcard);
                        list.add(map);
                    }
            else if (datelimit != null && !datelimit.equals(""))
                if (inidcard != null && !inidcard.equals(""))
                    for (Visitor visitor : visitors) {
                        Integer id = visitor.getId();
                        String aqcs = visitor.getAqcs();
                        Date ccsj = visitor.getCcsj();
                        String cph = visitor.getCph();
                        String cwgbh = visitor.getCwgbh();
                        String djy = visitor.getDjy();
                        String jcsj = visitor.getJcsj();
                        String jqbm = visitor.getJqbm();
                        String jqr = visitor.getJqr();
                        String khdw = visitor.getKhdw();
                        String kkhxm = visitor.getKhxm();
                        if (!khname.equals(kkhxm))
                            continue;
                        Date lfrq = visitor.getLfrq();
                        if (lfrq.getTime() < date1.getTime() || lfrq.getTime() > date2.getTime())
                            continue;
                        Integer lfryvxid = visitor.getLfryvxid();
                        String rcsy = visitor.getRcsy();
                        String idcard = visitor.getIdcard();
                        if (!inidcard.equals(idcard))
                            continue;
                        Integer sfjslc = visitor.getSfjslc();
                        String sjh = visitor.getSjh();
                        String zkwp = visitor.getZkwp();
                        Integer sfqylf = visitor.getSfqylf();
                        Integer qrrvxid = visitor.getQrrvxid();
                        String sfqylfstr = "未知";
                        String sfjslcstr = "未知";
                        if (sfqylf == 0) {
                            sfqylfstr = "未确认";
                        }
                        if (sfqylf == 1) {
                            sfqylfstr = "已确认";
                        }
                        if (sfqylf == 2) {
                            sfqylfstr = "已拒绝";
                        }
                        if (sfjslc == 0) {
                            sfjslcstr = "未结束";
                        }
                        if (sfjslc == 1) {
                            sfjslcstr = "已结束";
                        }
                        Map map = new HashMap();
                        map.put("id", id);
                        map.put("aqcs", aqcs);
                        if (ccsj != null)
                            map.put("ccsj", sdf.format(ccsj));
                        if (lfrq != null)
                            map.put("lfrq", sdf2.format(lfrq));
                        map.put("cph", cph);
                        map.put("cwgbh", cwgbh);
                        map.put("djy", djy);
                        map.put("jcsj", jcsj);
                        map.put("jqbm", jqbm);
                        map.put("jqr", jqr);
                        map.put("khdw", khdw);
                        map.put("kkhxm", kkhxm);
                        map.put("lfryvxid", lfryvxid);
                        map.put("rcsy", rcsy);
                        map.put("sfjslc", sfjslcstr);
                        map.put("sjh", sjh);
                        map.put("zkwp", zkwp);
                        map.put("sfqylf", sfqylfstr);
                        map.put("qrrvxid", qrrvxid);
                        map.put("idcard", idcard);
                        list.add(map);
                    }
                else
                    for (Visitor visitor : visitors) {
                        Integer id = visitor.getId();
                        String aqcs = visitor.getAqcs();
                        Date ccsj = visitor.getCcsj();
                        String cph = visitor.getCph();
                        String cwgbh = visitor.getCwgbh();
                        String djy = visitor.getDjy();
                        String jcsj = visitor.getJcsj();
                        String jqbm = visitor.getJqbm();
                        String jqr = visitor.getJqr();
                        String khdw = visitor.getKhdw();
                        String kkhxm = visitor.getKhxm();
                        if (!khname.equals(kkhxm))
                            continue;
                        Date lfrq = visitor.getLfrq();
                        if (lfrq.getTime() < date1.getTime() || lfrq.getTime() > date2.getTime())
                            continue;
                        Integer lfryvxid = visitor.getLfryvxid();
                        String rcsy = visitor.getRcsy();
                        String idcard = visitor.getIdcard();
                        Integer sfjslc = visitor.getSfjslc();
                        String sjh = visitor.getSjh();
                        String zkwp = visitor.getZkwp();
                        Integer sfqylf = visitor.getSfqylf();
                        Integer qrrvxid = visitor.getQrrvxid();
                        String sfqylfstr = "未知";
                        String sfjslcstr = "未知";
                        if (sfqylf == 0) {
                            sfqylfstr = "未确认";
                        }
                        if (sfqylf == 1) {
                            sfqylfstr = "已确认";
                        }
                        if (sfqylf == 2) {
                            sfqylfstr = "已拒绝";
                        }
                        if (sfjslc == 0) {
                            sfjslcstr = "未结束";
                        }
                        if (sfjslc == 1) {
                            sfjslcstr = "已结束";
                        }
                        Map map = new HashMap();
                        map.put("id", id);
                        map.put("aqcs", aqcs);
                        if (ccsj != null)
                            map.put("ccsj", sdf.format(ccsj));
                        if (lfrq != null)
                            map.put("lfrq", sdf2.format(lfrq));
                        map.put("cph", cph);
                        map.put("cwgbh", cwgbh);
                        map.put("djy", djy);
                        map.put("jcsj", jcsj);
                        map.put("jqbm", jqbm);
                        map.put("jqr", jqr);
                        map.put("khdw", khdw);
                        map.put("kkhxm", kkhxm);
                        map.put("lfryvxid", lfryvxid);
                        map.put("rcsy", rcsy);
                        map.put("sfjslc", sfjslcstr);
                        map.put("sjh", sjh);
                        map.put("zkwp", zkwp);
                        map.put("sfqylf", sfqylfstr);
                        map.put("qrrvxid", qrrvxid);
                        map.put("idcard", idcard);
                        list.add(map);
                    }
            else if (inidcard != null && !inidcard.equals(""))
                for (Visitor visitor : visitors) {
                    Integer id = visitor.getId();
                    String aqcs = visitor.getAqcs();
                    Date ccsj = visitor.getCcsj();
                    String cph = visitor.getCph();
                    String cwgbh = visitor.getCwgbh();
                    String djy = visitor.getDjy();
                    String jcsj = visitor.getJcsj();
                    String jqbm = visitor.getJqbm();
                    String jqr = visitor.getJqr();
                    String khdw = visitor.getKhdw();
                    String kkhxm = visitor.getKhxm();
                    if (!khname.equals(kkhxm))
                        continue;
                    Date lfrq = visitor.getLfrq();
                    Integer lfryvxid = visitor.getLfryvxid();
                    String rcsy = visitor.getRcsy();
                    String idcard = visitor.getIdcard();
                    if (!inidcard.equals(idcard))
                        continue;
                    Integer sfjslc = visitor.getSfjslc();
                    String sjh = visitor.getSjh();
                    String zkwp = visitor.getZkwp();
                    Integer sfqylf = visitor.getSfqylf();
                    Integer qrrvxid = visitor.getQrrvxid();
                    String sfqylfstr = "未知";
                    String sfjslcstr = "未知";
                    if (sfqylf == 0) {
                        sfqylfstr = "未确认";
                    }
                    if (sfqylf == 1) {
                        sfqylfstr = "已确认";
                    }
                    if (sfqylf == 2) {
                        sfqylfstr = "已拒绝";
                    }
                    if (sfjslc == 0) {
                        sfjslcstr = "未结束";
                    }
                    if (sfjslc == 1) {
                        sfjslcstr = "已结束";
                    }
                    Map map = new HashMap();
                    map.put("id", id);
                    map.put("aqcs", aqcs);
                    if (ccsj != null)
                        map.put("ccsj", sdf.format(ccsj));
                    if (lfrq != null)
                        map.put("lfrq", sdf2.format(lfrq));
                    map.put("cph", cph);
                    map.put("cwgbh", cwgbh);
                    map.put("djy", djy);
                    map.put("jcsj", jcsj);
                    map.put("jqbm", jqbm);
                    map.put("jqr", jqr);
                    map.put("khdw", khdw);
                    map.put("kkhxm", kkhxm);
                    map.put("lfryvxid", lfryvxid);
                    map.put("rcsy", rcsy);
                    map.put("sfjslc", sfjslcstr);
                    map.put("sjh", sjh);
                    map.put("zkwp", zkwp);
                    map.put("sfqylf", sfqylfstr);
                    map.put("qrrvxid", qrrvxid);
                    map.put("idcard", idcard);
                    list.add(map);
                }
            else
                for (Visitor visitor : visitors) {
                    Integer id = visitor.getId();
                    String aqcs = visitor.getAqcs();
                    Date ccsj = visitor.getCcsj();
                    String cph = visitor.getCph();
                    String cwgbh = visitor.getCwgbh();
                    String djy = visitor.getDjy();
                    String jcsj = visitor.getJcsj();
                    String jqbm = visitor.getJqbm();
                    String jqr = visitor.getJqr();
                    String khdw = visitor.getKhdw();
                    String kkhxm = visitor.getKhxm();
                    if (!khname.equals(kkhxm))
                        continue;
                    Date lfrq = visitor.getLfrq();
                    Integer lfryvxid = visitor.getLfryvxid();
                    String rcsy = visitor.getRcsy();
                    String idcard = visitor.getIdcard();
                    Integer sfjslc = visitor.getSfjslc();
                    String sjh = visitor.getSjh();
                    String zkwp = visitor.getZkwp();
                    Integer sfqylf = visitor.getSfqylf();
                    Integer qrrvxid = visitor.getQrrvxid();
                    String sfqylfstr = "未知";
                    String sfjslcstr = "未知";
                    if (sfqylf == 0) {
                        sfqylfstr = "未确认";
                    }
                    if (sfqylf == 1) {
                        sfqylfstr = "已确认";
                    }
                    if (sfqylf == 2) {
                        sfqylfstr = "已拒绝";
                    }
                    if (sfjslc == 0) {
                        sfjslcstr = "未结束";
                    }
                    if (sfjslc == 1) {
                        sfjslcstr = "已结束";
                    }
                    Map map = new HashMap();
                    map.put("id", id);
                    map.put("aqcs", aqcs);
                    if (ccsj != null)
                        map.put("ccsj", sdf.format(ccsj));
                    if (lfrq != null)
                        map.put("lfrq", sdf2.format(lfrq));
                    map.put("cph", cph);
                    map.put("cwgbh", cwgbh);
                    map.put("djy", djy);
                    map.put("jcsj", jcsj);
                    map.put("jqbm", jqbm);
                    map.put("jqr", jqr);
                    map.put("khdw", khdw);
                    map.put("kkhxm", kkhxm);
                    map.put("lfryvxid", lfryvxid);
                    map.put("rcsy", rcsy);
                    map.put("sfjslc", sfjslcstr);
                    map.put("sjh", sjh);
                    map.put("zkwp", zkwp);
                    map.put("sfqylf", sfqylfstr);
                    map.put("qrrvxid", qrrvxid);
                    map.put("idcard", idcard);
                    list.add(map);
                }
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        map1.put("code", 0);
        map1.put("msg", "ok");
        map1.put("data", list);
        return map1;
    }

    @ApiOperation(value = "查询来访登记门岗列表")
    @GetMapping("getlfdjbListmg")
    public Map<String, Object> getlfdjbListmg(@RequestParam(required = false) String khname, @RequestParam(required = false) String inidcard) {
        List<Visitor> visitors = lfdjbService.getLfdjbList();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        List<Map<String, Object>> list = new ArrayList<>();
        if (visitors != null) {
            if (khname != null && !khname.equals(""))
                if (inidcard != null && !inidcard.equals(""))
                    for (Visitor visitor : visitors) {
                        if (!String.valueOf(sim.format(visitor.getLfrq())).equals(sim.format(date)))
                            break;
                        Integer id = visitor.getId();
                        String aqcs = visitor.getAqcs();
                        Date ccsj = visitor.getCcsj();
                        String cph = visitor.getCph();
                        String cwgbh = visitor.getCwgbh();
                        String djy = visitor.getDjy();
                        String jcsj = visitor.getJcsj();
                        String jqbm = visitor.getJqbm();
                        String jqr = visitor.getJqr();
                        String khdw = visitor.getKhdw();
                        String kkhxm = visitor.getKhxm();
                        if (!kkhxm.equals(khname))
                            continue;
                        Date lfrq = visitor.getLfrq();
                        Integer lfryvxid = visitor.getLfryvxid();
                        String rcsy = visitor.getRcsy();
                        String idcard = visitor.getIdcard();
                        if (!inidcard.equals(idcard))
                            continue;
                        Integer sfjslc = visitor.getSfjslc();
                        if (sfjslc != 0)
                            continue;
                        String sjh = visitor.getSjh();
                        String zkwp = visitor.getZkwp();
                        Integer sfqylf = visitor.getSfqylf();
                        if (sfqylf != 1)
                            continue;
                        Integer qrrvxid = visitor.getQrrvxid();
                        if (qrrvxid == null)
                            continue;
                        String sfqylfstr = "未知";
                        String sfjslcstr = "未知";
                        if (sfqylf == 0) {
                            sfqylfstr = "未确认";
                        }
                        if (sfqylf == 1) {
                            sfqylfstr = "已确认";
                        }
                        if (sfqylf == 2) {
                            sfqylfstr = "已拒绝";
                        }
                        if (sfjslc == 0) {
                            sfjslcstr = "未结束";
                        }
                        if (sfjslc == 1) {
                            sfjslcstr = "已结束";
                        }
                        Map map = new HashMap();
                        map.put("id", id);
                        map.put("aqcs", aqcs);
                        map.put("ccsj", ccsj);
                        map.put("cph", cph);
                        map.put("cwgbh", cwgbh);
                        map.put("djy", djy);
                        map.put("jcsj", jcsj);
                        map.put("jqbm", jqbm);
                        map.put("jqr", jqr);
                        map.put("khdw", khdw);
                        map.put("kkhxm", kkhxm);
                        map.put("lfrq", lfrq);
                        map.put("lfryvxid", lfryvxid);
                        map.put("rcsy", rcsy);
                        map.put("sfjslc", sfjslcstr);
                        map.put("sjh", sjh);
                        map.put("zkwp", zkwp);
                        map.put("sfqylf", sfqylfstr);
                        map.put("qrrvxid", qrrvxid);
                        map.put("idcard", idcard);
                        list.add(map);
                    }
                else
                    for (Visitor visitor : visitors) {
                        if (!String.valueOf(sim.format(visitor.getLfrq())).equals(sim.format(date)))
                            break;
                        Integer id = visitor.getId();
                        String aqcs = visitor.getAqcs();
                        Date ccsj = visitor.getCcsj();
                        String cph = visitor.getCph();
                        String cwgbh = visitor.getCwgbh();
                        String djy = visitor.getDjy();
                        String jcsj = visitor.getJcsj();
                        String jqbm = visitor.getJqbm();
                        String jqr = visitor.getJqr();
                        String khdw = visitor.getKhdw();
                        String kkhxm = visitor.getKhxm();
                        if (!kkhxm.equals(khname))
                            continue;
                        Date lfrq = visitor.getLfrq();
                        Integer lfryvxid = visitor.getLfryvxid();
                        String rcsy = visitor.getRcsy();
                        String idcard = visitor.getIdcard();
                        Integer sfjslc = visitor.getSfjslc();
                        if (sfjslc != 0)
                            continue;
                        String sjh = visitor.getSjh();
                        String zkwp = visitor.getZkwp();
                        Integer sfqylf = visitor.getSfqylf();
                        if (sfqylf != 1)
                            continue;
                        Integer qrrvxid = visitor.getQrrvxid();
                        if (qrrvxid == null)
                            continue;
                        String sfqylfstr = "未知";
                        String sfjslcstr = "未知";
                        if (sfqylf == 0) {
                            sfqylfstr = "未确认";
                        }
                        if (sfqylf == 1) {
                            sfqylfstr = "已确认";
                        }
                        if (sfqylf == 2) {
                            sfqylfstr = "已拒绝";
                        }
                        if (sfjslc == 0) {
                            sfjslcstr = "未结束";
                        }
                        if (sfjslc == 1) {
                            sfjslcstr = "已结束";
                        }
                        Map map = new HashMap();
                        map.put("id", id);
                        map.put("aqcs", aqcs);
                        map.put("ccsj", ccsj);
                        map.put("cph", cph);
                        map.put("cwgbh", cwgbh);
                        map.put("djy", djy);
                        map.put("jcsj", jcsj);
                        map.put("jqbm", jqbm);
                        map.put("jqr", jqr);
                        map.put("khdw", khdw);
                        map.put("kkhxm", kkhxm);
                        map.put("lfrq", lfrq);
                        map.put("lfryvxid", lfryvxid);
                        map.put("rcsy", rcsy);
                        map.put("sfjslc", sfjslcstr);
                        map.put("sjh", sjh);
                        map.put("zkwp", zkwp);
                        map.put("sfqylf", sfqylfstr);
                        map.put("qrrvxid", qrrvxid);
                        map.put("idcard", idcard);
                        list.add(map);
                    }
            else if (inidcard != null && !inidcard.equals(""))
                for (Visitor visitor : visitors) {
                    if (!String.valueOf(sim.format(visitor.getLfrq())).equals(sim.format(date)))
                        break;
                    Integer id = visitor.getId();
                    String aqcs = visitor.getAqcs();
                    Date ccsj = visitor.getCcsj();
                    String cph = visitor.getCph();
                    String cwgbh = visitor.getCwgbh();
                    String djy = visitor.getDjy();
                    String jcsj = visitor.getJcsj();
                    String jqbm = visitor.getJqbm();
                    String jqr = visitor.getJqr();
                    String khdw = visitor.getKhdw();
                    String kkhxm = visitor.getKhxm();
                    Date lfrq = visitor.getLfrq();
                    Integer lfryvxid = visitor.getLfryvxid();
                    String rcsy = visitor.getRcsy();
                    String idcard = visitor.getIdcard();
                    if (!idcard.equals(inidcard))
                        continue;
                    Integer sfjslc = visitor.getSfjslc();
                    if (sfjslc != 0)
                        continue;
                    String sjh = visitor.getSjh();
                    String zkwp = visitor.getZkwp();
                    Integer sfqylf = visitor.getSfqylf();
                    if (sfqylf != 1)
                        continue;
                    Integer qrrvxid = visitor.getQrrvxid();
                    if (qrrvxid == null)
                        continue;
                    String sfqylfstr = "未知";
                    String sfjslcstr = "未知";
                    if (sfqylf == 0) {
                        sfqylfstr = "未确认";
                    }
                    if (sfqylf == 1) {
                        sfqylfstr = "已确认";
                    }
                    if (sfqylf == 2) {
                        sfqylfstr = "已拒绝";
                    }
                    if (sfjslc == 0) {
                        sfjslcstr = "未结束";
                    }
                    if (sfjslc == 1) {
                        sfjslcstr = "已结束";
                    }
                    Map map = new HashMap();
                    map.put("id", id);
                    map.put("aqcs", aqcs);
                    map.put("ccsj", ccsj);
                    map.put("cph", cph);
                    map.put("cwgbh", cwgbh);
                    map.put("djy", djy);
                    map.put("jcsj", jcsj);
                    map.put("jqbm", jqbm);
                    map.put("jqr", jqr);
                    map.put("khdw", khdw);
                    map.put("kkhxm", kkhxm);
                    map.put("lfrq", lfrq);
                    map.put("lfryvxid", lfryvxid);
                    map.put("rcsy", rcsy);
                    map.put("sfjslc", sfjslcstr);
                    map.put("sjh", sjh);
                    map.put("zkwp", zkwp);
                    map.put("sfqylf", sfqylfstr);
                    map.put("qrrvxid", qrrvxid);
                    map.put("idcard", idcard);
                    list.add(map);
                }
            else
                for (Visitor visitor : visitors) {
                    if (!String.valueOf(sim.format(visitor.getLfrq())).equals(sim.format(date)))
                        break;
                    Integer id = visitor.getId();
                    String aqcs = visitor.getAqcs();
                    Date ccsj = visitor.getCcsj();
                    String cph = visitor.getCph();
                    String cwgbh = visitor.getCwgbh();
                    String djy = visitor.getDjy();
                    String jcsj = visitor.getJcsj();
                    String jqbm = visitor.getJqbm();
                    String jqr = visitor.getJqr();
                    String khdw = visitor.getKhdw();
                    String kkhxm = visitor.getKhxm();
                    Date lfrq = visitor.getLfrq();
                    Integer lfryvxid = visitor.getLfryvxid();
                    String rcsy = visitor.getRcsy();
                    String idcard = visitor.getIdcard();
                    Integer sfjslc = visitor.getSfjslc();
                    if (sfjslc != 0)
                        continue;
                    String sjh = visitor.getSjh();
                    String zkwp = visitor.getZkwp();
                    Integer sfqylf = visitor.getSfqylf();
                    if (sfqylf != 1)
                        continue;
                    Integer qrrvxid = visitor.getQrrvxid();
                    if (qrrvxid == null)
                        continue;
                    String sfqylfstr = "未知";
                    String sfjslcstr = "未知";
                    if (sfqylf == 0) {
                        sfqylfstr = "未确认";
                    }
                    if (sfqylf == 1) {
                        sfqylfstr = "已确认";
                    }
                    if (sfqylf == 2) {
                        sfqylfstr = "已拒绝";
                    }
                    if (sfjslc == 0) {
                        sfjslcstr = "未结束";
                    }
                    if (sfjslc == 1) {
                        sfjslcstr = "已结束";
                    }
                    Map map = new HashMap();
                    map.put("id", id);
                    map.put("aqcs", aqcs);
                    map.put("ccsj", ccsj);
                    map.put("cph", cph);
                    map.put("cwgbh", cwgbh);
                    map.put("djy", djy);
                    map.put("jcsj", jcsj);
                    map.put("jqbm", jqbm);
                    map.put("jqr", jqr);
                    map.put("khdw", khdw);
                    map.put("kkhxm", kkhxm);
                    map.put("lfrq", lfrq);
                    map.put("lfryvxid", lfryvxid);
                    map.put("rcsy", rcsy);
                    map.put("sfjslc", sfjslcstr);
                    map.put("sjh", sjh);
                    map.put("zkwp", zkwp);
                    map.put("sfqylf", sfqylfstr);
                    map.put("qrrvxid", qrrvxid);
                    map.put("idcard", idcard);
                    list.add(map);
                }
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        map1.put("code", 0);
        map1.put("msg", "ok");
        map1.put("data", list);
        return map1;
    }

    @ApiOperation("查询内部账号")
    @GetMapping("selectnbzhgl")
    public Map<String, Object> selectnbzhgl() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> users = userService.getUserList();
        List<Map<String, Object>> list = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                Integer code = user.getCode();
                if (code == 0)
                    continue;
                Integer id = user.getId();
                String openid = user.getOpenid();
                String realname = user.getRealname();
                String zcsj = "未完成注册";
                if (user.getZcsj() != null) {
                    zcsj = simpleDateFormat.format(user.getZcsj());
                }

                Map map = new HashMap();
                map.put("id", id);
                map.put("openid", openid);
                map.put("realname", realname);
                map.put("zcsj", zcsj);
                list.add(map);
            }
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        map1.put("code", 0);
        map1.put("msg", "ok");
        map1.put("data", list);
        return map1;
    }

    @ApiOperation(value = "查询外部账号")
    @GetMapping("selectwbzhgl")
    public Map<String, Object> selectwbzhgl() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> users = userService.getUserList();
        List<Map<String, Object>> list = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                Integer code = user.getCode();
                if (code == 1)
                    continue;
                Integer id = user.getId();
                String openid = user.getOpenid();
                String realname = user.getRealname();
                String zcsj = simpleDateFormat.format(user.getZcsj());
                Map map = new HashMap();
                map.put("id", id);
                map.put("openid", openid);
                map.put("realname", realname);
                map.put("zcsj", zcsj);
                list.add(map);
            }
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        map1.put("code", 0);
        map1.put("msg", "ok");
        map1.put("data", list);
        return map1;
    }

    /*
     * 昨日访客数量、前日访客数量
     * 昨日完成访客流程、前日完成访客流程
     * 昨日平台服务总次数、前日平台服务总次数
     * 昨日内部用户数、前日内部用户数
     * 昨日外部用户数、前日外部用户数
     * 每日访客list[日期，数量]
     * 每日完成流程list[日期，数量]
     * 到访与未到访客list[日期，存在数量，不存在数量]
     * */
    @ApiOperation(value = "统计接口")
    @GetMapping("getdatav")
    public Map getDatav() {
        Map requestMap = new HashMap();
        Integer zrfks = 0, rqfks = 0, zrwclcs = 0, qrwclcs = 0, zrptfwcs = 0, qrptfwcs = 0, zrnbyhs = 0, qrnbyhs = 0, zrwbyhs = 0, qrwbyhs = 0;
        List<Map> datavlist = commonService.getdatavList();
        List mrfklist = new ArrayList();
        List mrwclclist = new ArrayList();
        List dfywdfklist = new ArrayList();
        Integer mrfk = 0, mrwclc = 0, fwcs = 0, nbyh = 0, wbyh = 0, dffk = 0, wdfk = 0;
        String datestr = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer i = 0;
        if (datavlist != null) {
            for (Map datavmap : datavlist) {
                i++;
                //zrfknum,nbunum,wbunum,servernum,tjrq,closeflownum,dffknum,wdfknum
                mrfk = Integer.parseInt(String.valueOf(datavmap.get("zrfknum")));
                mrwclc = Integer.parseInt(String.valueOf(datavmap.get("closeflownum")));
                fwcs = Integer.parseInt(String.valueOf(datavmap.get("servernum")));
                nbyh = Integer.parseInt(String.valueOf(datavmap.get("nbunum")));
                wbyh = Integer.parseInt(String.valueOf(datavmap.get("wbunum")));
                datestr = String.valueOf(datavmap.get("tjrq"));
                dffk = Integer.parseInt(String.valueOf(datavmap.get("dffknum")));
                wdfk = Integer.parseInt(String.valueOf(datavmap.get("wdfknum")));
                Map mrfkmap = new HashMap();
                mrfkmap.put("date", datestr);
                mrfkmap.put("num", mrfk);
                mrfklist.add(mrfkmap);

                Map mrwclcmap = new HashMap();
                mrwclcmap.put("date", datestr);
                mrwclcmap.put("num", mrwclc);
                mrwclclist.add(mrwclcmap);

                if (i < 31) {
                    Map dfywdfkmap = new HashMap();
                    dfywdfkmap.put("date", datestr);
                    dfywdfkmap.put("dfnum", dffk);
                    dfywdfkmap.put("wdnum", wdfk);
                    dfywdfklist.add(dfywdfkmap);
                    if (i < 3) {
                        if (i == 1) {
                            zrfks = mrfk;
                            zrwclcs = mrwclc;
                            zrptfwcs = fwcs;
                            zrnbyhs = nbyh;
                            zrwbyhs = wbyh;
                        } else {
                            rqfks = mrfk;
                            qrwclcs = mrwclc;
                            qrptfwcs = fwcs;
                            qrnbyhs = nbyh;
                            qrwbyhs = wbyh;
                        }
                    }
                }
                if (i > 366)
                    break;
            }
        }
        requestMap.put("zrfks", zrfks);
        requestMap.put("rqfks", rqfks);
        requestMap.put("zrwclcs", zrwclcs);
        requestMap.put("qrwclcs", qrwclcs);
        requestMap.put("zrptfwcs", zrptfwcs);
        requestMap.put("qrptfwcs", qrptfwcs);
        requestMap.put("zrnbyhs", zrnbyhs);
        requestMap.put("qrnbyhs", qrnbyhs);
        requestMap.put("zrwbyhs", zrwbyhs);
        requestMap.put("qrwbyhs", qrwbyhs);
        requestMap.put("mrfklist", mrfklist);
        requestMap.put("mrwclclist", mrwclclist);
        requestMap.put("dfywdfklist", dfywdfklist);
        return requestMap;
    }
}

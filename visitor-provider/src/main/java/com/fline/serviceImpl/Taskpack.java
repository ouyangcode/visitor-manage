package com.fline.serviceImpl;

import com.fline.mapper.CommonMapper;
import com.fline.model.Visitor;
import com.fline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@EnableScheduling
public class Taskpack {
    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private RedisTemplate<Object,Object> template;
    //3.添加定时任务
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
//    @Scheduled(fixedRate=5000)
    private void configureTasks() {
        template.setKeySerializer(new StringRedisSerializer());
        Integer zrfknum=0,nbunum=0,wbunum=0,servernum=0,closeflownum=0,wdfknum=0,dffknum=0;
        List<Map> datavmap = (List<Map>) template.opsForValue().get("datavList");
        if (datavmap!=null){
            servernum=Integer.parseInt(String.valueOf(datavmap.get(0).get("servernum")==null?0:datavmap.get(0).get("servernum")));
        }
        //格式化
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        List<Visitor> visitors = (List<Visitor>) template.opsForValue().get("lfdjbList");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-8);
        String yesterdayDate=sim.format(calendar.getTime());
        for (Visitor visitor : visitors){
            if(String.valueOf(sim.format(visitor.getLfrq())).equals(yesterdayDate)) {
                zrfknum++;
                if (visitor.getSfjslc()==1){
                    closeflownum++;
                }
                if (visitor.getRcsy()!=null&& visitor.getCcsj()!=null){
                    dffknum++;
                }
                else {
                    wdfknum++;
                }
            }
           else
               break;
        }
        servernum+=zrfknum;
        List<User> users = (List<User>) template.opsForValue().get("userList");
        for (User user : users){
            if(user.getCode()==0)
                wbunum++;
            else
                nbunum++;
        }
        Map paramMap = new HashMap();
        Date tjrq=new Date();
        paramMap.put("dffknum",dffknum);
        paramMap.put("wdfknum",wdfknum);
        paramMap.put("zrfknum",zrfknum);
        paramMap.put("nbunum",nbunum);
        paramMap.put("wbunum",wbunum);
        paramMap.put("servernum",servernum);
        paramMap.put("tjrq",calendar.getTime());
        paramMap.put("closeflownum",closeflownum);
        int i = commonMapper.insdatav(paramMap);
        if (i!=0) {
            List<Map> datavlist = commonMapper.getdatavList();
            template.opsForValue().set("datavList",datavlist);
            System.out.println(sim.format(tjrq) + "每日数据汇总完毕！");
        }
    }
}
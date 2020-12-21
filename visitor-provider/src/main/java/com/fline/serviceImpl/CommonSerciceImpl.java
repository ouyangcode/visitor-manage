package com.fline.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fline.mapper.CommonMapper;
import com.fline.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component
@Service
public class CommonSerciceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private RedisTemplate<Object,Object> template;

    @Override
    public String GetToken() {
        template.setKeySerializer(new StringRedisSerializer());
        String Token = String.valueOf(template.opsForValue().get("QiYeWeiXinToken"));
        return Token;
    }

    @Override
    public boolean SetToken(String Token) {
            template.setKeySerializer(new StringRedisSerializer());
            template.opsForValue().set("QiYeWeiXinToken",Token);
        return true;
    }

    @Override
    public List<Map> getdatavList() {
//        template.setStringSerializer(new StringRedisSerializer());
//        List<Map> list = (List<Map>) template.opsForValue().get("datavList");
//        if (list==null){
//            list = (List<Map>) template.opsForValue().get("datavList");
//            if (list==null){
//                synchronized (this){
//                    list = commonMapper.getdatavList();
//                    template.opsForValue().set("datavList",list);
//                }
//            }
//        }
        List<Map> list = commonMapper.getdatavList();
        return list;
    }

    @Override
    public Map getdatavById(Integer id) {
        return commonMapper.getdatavById(id);
    }

    @Override
    public Map getdatavByOutDay(Integer daynum) {
        template.setStringSerializer(new StringRedisSerializer());
        List<Map> list = (List<Map>) template.opsForValue().get("datavList");
        if (CollectionUtils.isEmpty(list)){
            list = (List<Map>) template.opsForValue().get("datavList");
            if (list==null){
                synchronized (this){
                    list = commonMapper.getdatavList();
                    template.opsForValue().set("datavList",list);
                    if (list==null)
                        return null;
                }
            }
        }
        return list.get(daynum);
    }
}

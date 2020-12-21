package com.fline.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fline.mapper.LfdjbMapper;
import com.fline.model.Visitor;
import com.fline.service.lfdjbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Service
public class lfdjbServiceImpl implements lfdjbService {

    @Autowired
    LfdjbMapper mapper;

    @Autowired
    RedisTemplate<Object,Object> template;
    @Override
    public List<Visitor> getLfdjbList() {
        template.setKeySerializer(new StringRedisSerializer());
        List<Visitor> list = (List<Visitor>) template.opsForValue().get("lfdjbList");
        if (CollectionUtils.isEmpty(list)){
            list = (List<Visitor>) template.opsForValue().get("lfdjbList");
            if (CollectionUtils.isEmpty(list)){
                synchronized (this){
                    list = mapper.getLfdjbList();
                    template.opsForValue().set("lfdjbList",list);
                }
            }
        }
        return mapper.getLfdjbList();
    }

    @Override
    public Visitor getLfdjbById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insLfdjb(Visitor visitor) {
        template.setKeySerializer(new StringRedisSerializer());
        int i = 0;
        i = mapper.insertSelective(visitor);
        if (i!=0){
            List<Visitor> list = mapper.getLfdjbList();
            template.opsForValue().set("lfdjbList",list);
        }
        return i;
    }

    @Override
    public int delLfdjb(int id) {
        template.setKeySerializer(new StringRedisSerializer());
        int i = 0;
        i = mapper.deleteByPrimaryKey(id);
        if (i!=0){
            List<Visitor> list = mapper.getLfdjbList();
            template.opsForValue().set("lfdjbList",list);
        }
        return  i ;
    }

    @Override
    public int upLfdjb(Visitor visitor) {
        template.setKeySerializer(new StringRedisSerializer());
        int i = 0;
        i = mapper.updateByPrimaryKeySelective(visitor);
        if (i!=0){
            List<Visitor> list = mapper.getLfdjbList();
            template.opsForValue().set("lfdjbList",list);
        }
        return  i ;
    }
}

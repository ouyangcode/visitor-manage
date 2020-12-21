package com.fline.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fline.mapper.UserMapper;
import com.fline.model.User;
import com.fline.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class userServiceImpl implements userService {

    @Autowired
    UserMapper mapper;

    @Autowired
    RedisTemplate<Object,Object> template;
    @Override
    public List<User> getUserList() {
//        template.setKeySerializer(new StringRedisSerializer());
//        List<User> list = (List<User>) template.opsForValue().get("userList");
//        if (CollectionUtils.isEmpty(list)){
//            list = (List<User>) template.opsForValue().get("userList");
//            if (CollectionUtils.isEmpty(list)){
//                synchronized (this){
//                    list = mapper.getUserList();
//                    template.opsForValue().set("userList",list);
//                }
//            }
//        }
        List<User> list = mapper.getUserList();
        return list;
    }

    @Override
    public User getUserById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByOpenId(String openid) {
        return mapper.getUserByOpenId(openid);
    }

    @Override
    public int insUser(User user) {
        template.setKeySerializer(new StringRedisSerializer());
        int i = 0;
        i = mapper.insertSelective(user);
        if (i!=0){
            List<User> list = mapper.getUserList();
            template.opsForValue().set("userList",list);
        }
        return i;
    }

    @Override
    public int delUser(int id) {
        template.setKeySerializer(new StringRedisSerializer());
        int i = 0;
        i = mapper.deleteByPrimaryKey(id);
        if (i!=0){
            List<User> list = mapper.getUserList();
            template.opsForValue().set("userList",list);
        }
        return i;
    }

    @Override
    public int upUser(User user) {
        template.setKeySerializer(new StringRedisSerializer());
        int i = 0;
        i = mapper.updateByPrimaryKeySelective(user);
        if (i!=0){
            List<User> list = mapper.getUserList();
            template.opsForValue().set("userList",list);
        }
        return i;
    }
}

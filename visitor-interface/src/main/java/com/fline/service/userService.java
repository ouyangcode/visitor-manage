package com.fline.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fline.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public interface userService {
    List<User> getUserList();

    User getUserById(int id);

    User getUserByOpenId(String openid);

    int insUser(User user);

    int delUser(int id);

    int upUser(User user);
}

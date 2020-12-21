package com.fline.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fline.model.Visitor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public interface lfdjbService {

    /**
     * 获取来访登记表列表
     * @return
     */
    List<Visitor> getLfdjbList();

    /**
     * 根据id获取来访登记表
     * @param id
     * @return
     */
    Visitor getLfdjbById(int id);

    /**
     * 增加来访登记表
     * @param visitor
     * @return
     */
    int insLfdjb(Visitor visitor);

    /**
     * 删除来访登记表
     * @param id
     * @return
     */
    int delLfdjb(int id);

    /**
     * 修改来访登记表
     * @param visitor
     * @return
     */
    int upLfdjb(Visitor visitor);
}

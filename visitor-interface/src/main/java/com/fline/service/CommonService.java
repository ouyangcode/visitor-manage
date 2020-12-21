package com.fline.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Service
public interface CommonService {

    String GetToken();
    boolean SetToken(String Token);

    List<Map> getdatavList();

    Map getdatavById(Integer id);

    Map getdatavByOutDay(Integer daynum);
}

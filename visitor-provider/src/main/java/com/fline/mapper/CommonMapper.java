package com.fline.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface CommonMapper {
    int insdatav(Map map);

    Map getdatavById(Integer id);

    List<Map> getdatavList();
}

package com.fline.mapper;

import com.fline.model.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LfdjbMapper {

    List<Visitor> getLfdjbList();

    int deleteByPrimaryKey(Integer id);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    Visitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);
}
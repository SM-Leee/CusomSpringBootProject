package com.example.springtest.service.mapper;

import com.example.springtest.annotation.HisBeanMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Object> pidSelect();
}

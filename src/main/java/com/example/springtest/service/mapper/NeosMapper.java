package com.example.springtest.service.mapper;

import com.example.springtest.annotation.NeosBeanMapper;

import java.util.List;

@NeosBeanMapper
public interface NeosMapper {
    List<Object> compSelect();
}

package com.example.springtest.service.service;

import com.example.springtest.service.mapper.NeosMapper;
import com.example.springtest.service.mapper.TestMapper;
import com.example.springtest.service.service.iService.INeosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeosService implements INeosService {

    @Autowired
    NeosMapper mapper;

    @Autowired
    TestMapper testMapper;

    @Override
    public List<Object> comp() {
        return mapper.compSelect();
    } 

    @Override
    public List<Object> his() {
        return testMapper.pidSelect();
    }

}

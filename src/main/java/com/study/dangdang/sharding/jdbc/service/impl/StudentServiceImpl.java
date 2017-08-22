package com.study.dangdang.sharding.jdbc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.study.dangdang.sharding.jdbc.entity.Student;
import com.study.dangdang.sharding.jdbc.mapper.StudentMapper;
import com.study.dangdang.sharding.jdbc.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
    
    @Resource
    public StudentMapper studentMapper;

    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0 ? true : false;
    }

}

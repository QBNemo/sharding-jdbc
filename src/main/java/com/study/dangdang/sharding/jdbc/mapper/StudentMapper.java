package com.study.dangdang.sharding.jdbc.mapper;

import java.util.List;

import com.study.dangdang.sharding.jdbc.entity.Student;

public interface StudentMapper {
    
    Integer insert(Student s);
    
    List<Student> findAll();
    
    List<Student> findByStudentIds(List<Integer> studentIds);

}

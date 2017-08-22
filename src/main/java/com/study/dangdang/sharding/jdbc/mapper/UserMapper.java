package com.study.dangdang.sharding.jdbc.mapper;

import java.util.List;

import com.study.dangdang.sharding.jdbc.entity.User;

public interface UserMapper {
    
    Integer insert(User u);
    
    Integer insertList(List<User> users);
    
    List<User> findAll();
    
    List<User> findByUserIds(List<Integer> userIds);
    
    List<User> findByUserAge(Integer age1, Integer age2);
    
    List<User> findByUserId12(Integer id1, Integer id2);
    
    User findByUserId(Integer id);
}

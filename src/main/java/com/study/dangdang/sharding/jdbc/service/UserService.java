package com.study.dangdang.sharding.jdbc.service;

import java.util.List;

import com.study.dangdang.sharding.jdbc.entity.User;

public interface UserService {
    
    public boolean insert(User u);
    
    public boolean insertList(List<User> uers);
    
    public List<User> findAll();
    
    public List<User> findByUserIds(List<Integer> ids);
    
    public List<User> findByUserAge(Integer age1, Integer age2);
    
    public List<User> findByUserId12(Integer id1, Integer id2);
    
    public User findByUserId(Integer id);
    
    public void transactionTestSucess();
    
    public void transactionTestFailure() throws IllegalAccessException;

}

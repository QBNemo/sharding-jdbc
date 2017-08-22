package com.study.dangdang.sharding.jdbc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.dangdang.sharding.jdbc.entity.Student;
import com.study.dangdang.sharding.jdbc.entity.User;
import com.study.dangdang.sharding.jdbc.mapper.StudentMapper;
import com.study.dangdang.sharding.jdbc.mapper.UserMapper;
import com.study.dangdang.sharding.jdbc.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;
    
    @Resource
    public StudentMapper studentMapper;

    public boolean insert(User u) {
        return userMapper.insert(u) > 0 ? true :false;
    }

    public boolean insertList(List<User> users) {
        return userMapper.insertList(users) > 0 ? true :false;
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }

    public List<User> findByUserIds(List<Integer> ids) {
        return userMapper.findByUserIds(ids);
    }

	public List<User> findByUserAge(Integer age1, Integer age2) {
		return userMapper.findByUserAge(age1, age2);
	}
	
	public List<User> findByUserId12(Integer id1, Integer id2) {
		return userMapper.findByUserId12(id1, id2);
	}
	
	public User findByUserId(Integer id) {
		return userMapper.findByUserId(id);
	}
	
    @Transactional(propagation=Propagation.REQUIRED)
    public void transactionTestSucess() {
        User u = new User();
        u.setUserId(13);
        u.setAge(25);
        u.setName("war3 1.27");
        userMapper.insert(u);
        
        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe");
        studentMapper.insert(student);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void transactionTestFailure() throws IllegalAccessException {
        User u = new User();
        u.setUserId(13);
        u.setAge(25);
        u.setName("war3 1.27 good");
        userMapper.insert(u);
        
        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe1");
        studentMapper.insert(student);
        throw new IllegalAccessException();
    }
}

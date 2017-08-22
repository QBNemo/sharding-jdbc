package com.study.dangdang.sharding.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.dangdang.sharding.jdbc.entity.Student;
import com.study.dangdang.sharding.jdbc.entity.User;
import com.study.dangdang.sharding.jdbc.service.StudentService;
import com.study.dangdang.sharding.jdbc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/spring/spring-database.xml",
                                    "classpath*:config/spring/spring-sharding.xml" })
public class ShardingJdbcMybatisTest {

    @Resource
    public UserService userService;
    
    @Resource
    public StudentService studentService;
    
    //@Test
    public void testUserInsert() {
    	for(int i=1; i<=15; i++) {
            User u = new User();
            u.setUserId(i);
            u.setAge(100 + i);
            u.setName(i%2 + "-" + i%3);
            Assert.assertEquals(userService.insert(u), true);
    	}
    }
    
    @Test
    public void testUserInsertList() {
    	List<User> users = new ArrayList<User>();
        User u = new User();
        u.setUserId(13);
        u.setAge(100 + 13);
        u.setName(13%2 + "-" + 13%3);
        users.add(u);
        
        u = new User();
        u.setUserId(14);
        u.setAge(100 + 14);
        u.setName(14%2 + "-" + 14%3);
        users.add(u);
        
        u = new User();
        u.setUserId(15);
        u.setAge(100 + 15);
        u.setName(15%2 + "-" + 15%3);
        users.add(u);

        Assert.assertEquals(userService.insertList(users), true);
    }
    
    //@Test
    public void testStudentInsert() {
        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe");
        Assert.assertEquals(studentService.insert(student), true);
    }

    //@Test
    public void testFindAll(){
        List<User> users = userService.findAll();
        if(null != users && !users.isEmpty()){
            for(User u :users){
                System.out.println(u);
            }
        }
    }
    
    //@Test
    public void testFindById() {
        User user = userService.findByUserId(3);
        if(null != user){
            System.out.println(user);
        } else {
        	System.out.println("NULL USER");
        }
    }
    
    //@Test
    public void testSQLIN(){
        List<User> users = userService.findByUserIds(Arrays.asList(12,13,14));
        if(null != users && !users.isEmpty()){
            for(User u :users){
                System.out.println(u);
            }
        } else {
        	System.out.println("Empty or null");
        }
    }
    
    //@Test
    public void testSQLBetweenAge(){
        List<User> users = userService.findByUserAge(101, 103);
        if(null != users && !users.isEmpty()){
            for(User u :users){
                System.out.println(u);
            }
        } else {
        	System.out.println("Empty or null");
        }
    }
    
    //@Test
    public void testSQLBetweenId(){
        List<User> users = userService.findByUserId12(1, 3);
        if(null != users && !users.isEmpty()){
            for(User u :users){
                System.out.println(u);
            }
        } else {
        	System.out.println("Empty or null");
        }
    }
    
    //@Test
    public void testTransactionTestSucess(){
        userService.transactionTestSucess();
    }
    
    //@Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException{
        userService.transactionTestFailure();
    }
    
}

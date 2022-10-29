package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {


    @Autowired
    ApplicationContext context;
    
    @Test
    void addAndGet() {
        //UserDao userDao = new UserDao(new AwsConnectionMaker()); //인터페이스로 생성


        //UserDaoFactory udf = new UserDaoFactory();
        //UserDao userDao = udf.awsUserDao();  //팩토리를 사용한 userdao 생성

        UserDao userDao = context.getBean("awsUserDao",UserDao.class); //spring기능 
        //userDao.add(new User("1","hwan","1234"));
        User user = userDao.get("1");
        assertEquals("hwan",user.getName());

    }


}
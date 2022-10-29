package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {


    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User user1;
    User user2;
    User user3;
    @BeforeEach
    void setUP(){
        userDao=context.getBean("awsUserDao",UserDao.class);
        user1= new User("1","jung","1234");
        user2= new User("2","dae","1234");
        user3= new User("3","hwan","1234");
    }
    
    @Test
    void addAndGet() {
        //UserDao userDao = new UserDao(new AwsConnectionMaker()); //인터페이스로 생성


        //UserDaoFactory udf = new UserDaoFactory();
        //UserDao userDao = udf.awsUserDao();  //팩토리를 사용한 userdao 생성

        UserDao userDao = context.getBean("awsUserDao",UserDao.class); //spring기능
        userDao.add(user2);
        User user = userDao.get("1");
        assertEquals("hwan",user.getName());

    }

    @Test
    void getCount() throws SQLException, ClassNotFoundException {

        assertEquals(0,userDao.getCount());



    }


}
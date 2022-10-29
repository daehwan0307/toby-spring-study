package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {



    @Test
    void addAndGet() {
        //UserDao userDao = new UserDao(new AwsConnectionMaker()); //인터페이스로 생성


        UserDaoFactory udf = new UserDaoFactory();
        UserDao userDao = udf.awsUserDao();  //팩토리를 사용한 userdao 생성

        //userDao.add(new User("1","hwan","1234"));
        User user = userDao.get("1");
        assertEquals("hwan",user.getName());

    }


}
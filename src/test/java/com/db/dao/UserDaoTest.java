package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    ConnectionMaker connectionMaker = new AwsConnectionMaker();


    @Test
    void addAndGet() {
        UserDao userDao = new UserDao(connectionMaker);
       // userDao.add(new User("1","hwan","1234"));
        User user = userDao.get("1");
        assertEquals("hwan",user.getName());
        userDao.deleteAll();
    }


}
package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {



    @Test
    void addAndGet() {
        UserDao userDao = new UserDao(new AwsConnectionMaker()); //userDao 를 생상허면서 AwsConnectionMaker도 생성한다.
        //userDao.add(new User("1","hwan","1234"));
        User user = userDao.get("1");
        assertEquals("hwan",user.getName());

    }


}
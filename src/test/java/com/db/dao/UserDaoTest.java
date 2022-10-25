package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndSelect() {
        UserDao userDao = new UserDao();
        String id ="10";
      //  userDao.add(new User(id,"hwan","1234"));
        User user = userDao.findById(id);
        Assertions.assertEquals("hwan",user.getName());

    }
}
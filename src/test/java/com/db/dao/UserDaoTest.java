package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void addAndGet() {
        UserDao userDao = new UserDaoFactory().awsUserDao();
        String id = "5";
        //userDao.add(new User(id, "hwan", "password"));
        User user = userDao.findById(id);
        assertEquals("hwan", user.getName());
        assertEquals("password", user.getPassword());
    }



}
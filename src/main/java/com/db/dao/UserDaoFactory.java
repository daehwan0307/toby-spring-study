package com.db.dao;

public class UserDaoFactory {
    public UserDao userDao(){
        ConnectionMaker connectionMaker = new AwsConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}

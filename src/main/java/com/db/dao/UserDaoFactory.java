package com.db.dao;

public class UserDaoFactory {
    public UserDao awsUserDao(){  //awsUserDao를
        return new UserDao(new AwsConnectionMaker());
    }

   // public UserDao localUserDao(){
   //     return new UserDao(new LocalConnectionMaker());
    // }
}

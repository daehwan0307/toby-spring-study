package com.db.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao(){  //awsUserDao를
        return new UserDao(new AwsConnectionMaker());
    }

   // public UserDao localUserDao(){
   //     return new UserDao(new LocalConnectionMaker());
    // }
}

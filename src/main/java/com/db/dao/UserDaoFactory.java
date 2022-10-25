package com.db.dao;

import com.db.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao userDao(){
    return new UserDao(connectionMaker());

    }

    @Bean
    public UserDao awsUserDao(){
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new AwsConnectionMaker();
    }
}

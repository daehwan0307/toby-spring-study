package com.db.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao(){  //awsUserDao를
        return new UserDao(awsdataSource());
    }

     DataSource awsdataSource(){
        Map<String,String> env = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUrl(env.get("DB_NAME"));
        dataSource.setUrl(env.get("DB_PASSWORD"));

        return dataSource;
    }


}

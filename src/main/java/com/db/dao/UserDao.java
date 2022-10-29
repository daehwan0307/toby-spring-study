package com.db.dao;

import com.db.domain.User;


import java.sql.*;
import java.util.Map;

public class UserDao {
    private ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker){

        this.connectionMaker = connectionMaker;
    }

    public void add(User user) {


        try {
            // DB접속 (ex sql workbeanch실행)
          Connection c = connectionMaker.getConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            // Query문 실행
            pstmt.executeUpdate();

            pstmt.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteAll() {

       try{
           Connection c = connectionMaker.getConnection();

           PreparedStatement pstmt = c.prepareStatement("DELETE FROM  users");

           pstmt.executeUpdate();
           pstmt.close();
           c.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
    }

    public User get(String id) {


        try {
            // DB접속 (ex sql workbeanch실행)
            Connection c = connectionMaker.getConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query문 실행
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));

            rs.close();
            pstmt.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("SELECT count(*) FROM users");

        ResultSet rs = ps.executeQuery();
        rs.next();

        int cnt = rs.getInt(1);

        return cnt;
    }

    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new AwsConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
//        userDao.add();
        User user = userDao.get("1");
        System.out.println(user.getName());
    }
}


package com.db.dao;

import com.db.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;


import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class UserDao {
    private ConnectionMaker connectionMaker;

    private DataSource dataSource;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public UserDao(ConnectionMaker connectionMaker) {

        this.connectionMaker = connectionMaker;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy st) {

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = st.getStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }




    public void add(User user) {

        jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement getStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });

    }
    public void deleteAll() {
        jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement getStatement(Connection c) throws SQLException {

                PreparedStatement ps =c.prepareStatement("DELETE FROM users");
                return ps;
            }
        });
    }

    public User get(String id) {


        try {
            // DB?????? (ex sql workbeanch??????)
            Connection c = connectionMaker.getConnection();

            // Query??? ??????
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query??? ??????
            ResultSet rs = pstmt.executeQuery();
            User user = null;
            if(rs.next()){
                 user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("password"));
            }


            rs.close();
            pstmt.close();
            c.close();
            if(user == null){
                throw new EmptyResultDataAccessException(1);
            }


            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("SELECT count(*) FROM users");

        ResultSet rs = ps.executeQuery();
        rs.next();

        int cnt = rs.getInt(1);
        rs.close();
        ps.close();
        c.close();

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


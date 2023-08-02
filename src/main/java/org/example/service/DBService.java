package org.example.service;

import org.example.db.UserDAO;
import org.example.user.User;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.*;

public class DBService implements AutoCloseable{
    private final Connection connection;
    private final UserDAO userDAO;
    public enum DB{H2, Postgres}
    public DBService(DB db) throws SQLException {
        this.connection = switch (db) {
            case H2 -> getH2DBConnection();
            case Postgres -> getPostgresConnection();
        };
        this.userDAO = new UserDAO(connection);
    }

    public void init() throws SQLException {
        userDAO.createTable();
    }

    public void saveUser(User user) throws SQLException {
        userDAO.saveUser(user);
    }

    public boolean isExistingLoginPassword(String login, String password) throws SQLException {
        return userDAO.hasUserWithLoginPassword(login, password);
    }

    @Override
    public void close() throws Exception {
        if(!connection.isClosed()) connection.close();
    }

    private static Connection getH2DBConnection() throws SQLException {
        String url = "jdbc:h2:./h2db";
        String name = "test";
        String pass = "test";

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);
        ds.setUser(name);
        ds.setPassword(pass);

        return DriverManager.getConnection(url, name, pass);
    }

    private static Connection getPostgresConnection() throws SQLException {
        return null;
    }

}

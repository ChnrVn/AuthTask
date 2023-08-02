package org.example.service;

import org.example.user.User;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

public class AccountService {
    private final DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
    }

    public boolean checkAuth(String login, String password) throws AuthenticationException {
        try {
            return dbService.isExistingLoginPassword(login, password);
        } catch (SQLException e) {
            throw new AuthenticationException(e.getSQLState());
        }
    }
    public void saveUser(User user) throws AuthenticationException {
        try {
            dbService.saveUser(user);
        } catch (SQLException e) {
            throw new AuthenticationException(e.getSQLState());
        }
    }
}

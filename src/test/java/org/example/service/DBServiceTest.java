package org.example.service;

import org.example.user.User;
import org.example.user.UserLogin;
import org.example.user.UserPassword;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBServiceTest {

    @Test
    public void saveAndCheckTest(){
        try(DBService service = new DBService(DBService.DB.H2)){
            service.saveUser(User.of(
                    UserLogin.of("login"),
                    UserPassword.of("password")));
            service.saveUser(User.of(
                    UserLogin.of("login1"),
                    UserPassword.of("password1")));


            Assert.assertTrue(
                    service.isExistingLoginPassword("login", "password")
            );

            Assert.assertFalse(
                    service.isExistingLoginPassword("login", "password1")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
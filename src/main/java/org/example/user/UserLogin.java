package org.example.user;

import java.util.Objects;

// Wrapper for login
public class UserLogin {
    private final String loginStr;

    private UserLogin(String login) {
        this.loginStr = login;
    }

    public static UserLogin of(String login){
        return new UserLogin(login);
    }
    public String asString() {
        return loginStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLogin userLogin)) return false;
        return Objects.equals(loginStr, userLogin.loginStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginStr);
    }
}

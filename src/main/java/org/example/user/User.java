package org.example.user;

public class User {
    private final String password;
    private final String login;

    private User(UserLogin login, UserPassword password) {
        this.password = password.asString();
        this.login = login.asString();
    }

    //2 strings in 1 constructor -> errors with args order
    //that's why wrappers
    public static User of(UserLogin login, UserPassword password){
        return new User(login, password);
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}

package org.example.user;

import java.util.Objects;

// Wrapper for password
public class UserPassword {
    private final String password;

    private UserPassword(String password) {

        this.password = password;
    }

    public static UserPassword of(String password){
        return new UserPassword(password);
    }
    public String asString() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPassword that)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}

package org.example.db;

import org.example.user.User;

import java.sql.*;

public class UserDAO {
    private final Connection connection;
    private final Executor executor;
    public UserDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.executor = new Executor(connection);
        createTable();
    }

    public void createTable() throws SQLException {
        executor.execute(
        """
            create table if not exists users 
            (id bigint auto_increment, 
            login varchar(256), 
            password varchar(256),
            primary key (id)); 
            """
        );
    }
    public void saveUser(User user) throws SQLException {
        executor.executePrep(
                """
                insert into USERS 
                (LOGIN, PASSWORD) values 
                (?, ?)  
                """,
                (p) -> p.setString(1, user.getLogin()),
                (p) -> p.setString(2, user.getPassword())
        );
    }

    public boolean hasUserWithLoginPassword(String login, String password) throws SQLException {
        return executor.executePrepQuery(
                ResultSet::next,
                " SELECT * from USERS where LOGIN = ? and PASSWORD = ? ",
                (p) -> p.setString(1, login),
                (p) -> p.setString(2, password)
        );
    }
}

package org.example.db;

import java.sql.*;

public class Executor {

    Connection connection;

    public Executor(Connection connection){
        this.connection = connection;
    }

    public void execute(String sql)
            throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
        }
    }

    public<T> T executeQuery(String sql,
                             ResSetExtractor<T> resultExtractor)
            throws SQLException {
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            return resultExtractor.extract(resultSet);
        }
    }

    public void executePrep(String sqlPrep,
                            PreparedStatementSetter... settings)
            throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(sqlPrep)){
            for(PreparedStatementSetter setting : settings){
                setting.set(statement);
            }
            statement.execute();
        }
    }

    public<T> T executePrepQuery(ResSetExtractor<T> resultExtractor,
                                 String sqlPrep,
                                 PreparedStatementSetter... settings)
            throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(sqlPrep)){

            for(PreparedStatementSetter setting : settings){
                setting.set(statement);
            }

            ResultSet resultSet = statement.executeQuery();
            T res = resultExtractor.extract(resultSet);
            resultSet.close();
            return res;
        }
    }
}

/*
PreparedStatement Consumer throwing SQLException.
Allows methods in Executor class receive string of prepared statement
and PreparedStatementSetter massive to set all placeholders in PreparedStatement.
So whole control of creating, executing and closing statements remains in Executor class.
 */
@FunctionalInterface
interface PreparedStatementSetter{
    void set(PreparedStatement p) throws SQLException;
}

/*
ResultSet to T function throwing SQLException.
Allows Executor methods return arbitrary result T
extracting from ResultSet the way client want
 */
@FunctionalInterface
interface ResSetExtractor<T>{
    T extract(ResultSet result) throws SQLException;
}
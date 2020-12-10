package org.example.dao;

import org.example.User;

import java.sql.SQLException;
import javax.sql.DataSource;

public interface UserDAO {

    public void setDataSource(DataSource ds) throws SQLException;

    public void addUser(String firstName, String lastName);

    public User getUserByName(String firstName, String lastName);
}
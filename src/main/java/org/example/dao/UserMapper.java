package org.example.dao;

import org.example.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    private String USER_ID_COLUMN = "user_id";
    private String FIRSTNAME_COLUMN = "firstname";
    private String LASTNAME_COLUMN = "lastname";
    private String AGE_COLUMN = "age";

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString(USER_ID_COLUMN));
        user.setFirstName(rs.getString(FIRSTNAME_COLUMN));
        user.setLastName(rs.getString(LASTNAME_COLUMN));
        user.setAge(rs.getInt(AGE_COLUMN));
        return user;
    }
}
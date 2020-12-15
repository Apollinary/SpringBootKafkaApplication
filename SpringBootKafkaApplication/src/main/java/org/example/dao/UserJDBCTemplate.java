package org.example.dao;

import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserJDBCTemplate implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplateObject;

    public UserJDBCTemplate() {
    }

    public void initDataBase() {
        try {
            jdbcTemplateObject.execute(new BufferedReader(new FileReader("src/main/resources/tables.sql")).lines().collect(Collectors.joining()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String firstName, String lastName, int age) {
        String sqlQuery = "insert into users (firstname, lastname, age) values (?, ?, ?)";
        jdbcTemplateObject.update(sqlQuery, firstName, lastName, age);
        System.out.println("Created Record firstName = " + firstName + " lastName = " + lastName);
    }

    @Override
    public void addMessage(long userId, String messageText, long timestamp, String device) {
        String sqlQuery = "insert into messages (user_id, message_txt, message_timestamp, device) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(sqlQuery, userId, messageText, timestamp, device);
    }

    @Override
    public List<User> getUserByName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM users where firstname = ? and lastname = ?";
        return jdbcTemplateObject.query(sqlQuery, new UserMapper(), firstName, lastName);
    }
}
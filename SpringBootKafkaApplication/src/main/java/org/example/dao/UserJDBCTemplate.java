package org.example.dao;

import org.example.Comment;
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
    public void addComment(long userId, String commentText, long timestamp, String product) {
        String sqlQuery = "insert into comments (user_id, comment_txt, comment_timestamp, product) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(sqlQuery, userId, commentText, timestamp, product);
    }

    @Override
    public List<User> getUserByName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM users where firstname = ? and lastname = ?";
        return jdbcTemplateObject.query(sqlQuery, new UserMapper(), firstName, lastName);
    }

    @Override
    public List<Comment> getCommentsByUserId(long userId, String product) {
        String sqlQuery = "SELECT * FROM comments where user_id = ? and product = ?";
        return jdbcTemplateObject.query(sqlQuery, new CommentMapper(), userId, product);
    }

    @Override
    public List<Comment> getCommentsByUserName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM comments where user_id = (select user_id from users where firstname = ? and lastname = ?)";
        return jdbcTemplateObject.query(sqlQuery, new CommentMapper(), firstName, lastName);
    }

    @Override
    public List<Comment> getCommentsByProductName(String productName) {
        String sqlQuery = "SELECT * FROM comments where product = ?";
        return jdbcTemplateObject.query(sqlQuery, new CommentMapper(), productName);
    }
}
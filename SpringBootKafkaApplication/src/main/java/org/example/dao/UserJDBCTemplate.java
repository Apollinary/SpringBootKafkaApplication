package org.example.dao;

import org.example.model.Comment;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserJDBCTemplate {

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

    public void addUser(String firstName, String lastName, int age) {
        String sqlQuery = "insert into users (firstname, lastname, age) values (?, ?, ?)";
        jdbcTemplateObject.update(sqlQuery, firstName, lastName, age);
    }

    public void addComment(long userId, String commentText, long timestamp, String product) {
        String sqlQuery = "insert into comments (user_id, comment_txt, comment_timestamp, product) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(sqlQuery, userId, commentText, timestamp, product);
    }

    public List<User> getUserByName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM users where firstname = ? and lastname = ?";
        return jdbcTemplateObject.query(sqlQuery, new UserMapper(), firstName, lastName);
    }

    public List<Comment> getCommentsByUserName(String firstName, String lastName) {
        String sqlQuery = "SELECT co.comment_id, co.user_id, us.firstname, us.lastname, co.comment_txt, co.comment_timestamp, co.product, us.age "
                + "FROM comments co INNER JOIN users us ON co.user_id=us.user_id where us.firstname= ? and us.lastname = ?";
        return jdbcTemplateObject.query(sqlQuery, new CommentMapper(), firstName, lastName);
    }

    public List<Comment> getCommentsByProductName(String productName) {
        String sqlQuery = "SELECT co.comment_id, co.user_id, us.firstname, us.lastname, co.comment_txt, co.comment_timestamp, co.product, us.age "
                + "FROM comments co INNER JOIN users us ON co.user_id=us.user_id where co.product = ?";
        return jdbcTemplateObject.query(sqlQuery, new CommentMapper(), productName);
    }
}
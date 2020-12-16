package org.example.dao;

import org.example.Comment;
import org.example.User;

import java.util.List;

public interface UserDAO {

    public void addUser(String firstName, String lastName, int age);

    public void addComment(long userId, String commentText, long timestamp, String device);

    public List<User> getUserByName(String firstName, String lastName);

    public List<Comment> getCommentsByUserId(long userId, String product);

    public List<Comment> getCommentsByUserName(String firstName, String lastName);

    public List<Comment> getCommentsByProductName(String productName);
}
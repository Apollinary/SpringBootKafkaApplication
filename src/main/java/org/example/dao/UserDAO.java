package org.example.dao;

import org.example.User;

import java.util.List;

public interface UserDAO {

    public void addUser(String firstName, String lastName, int age);

    public void addMessage(long userId, String messageText, long timestamp, String device);

    public List<User> getUserByName(String firstName, String lastName);
}
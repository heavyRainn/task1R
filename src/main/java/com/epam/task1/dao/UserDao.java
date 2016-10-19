package com.epam.task1.dao;

public interface UserDao {

    boolean authenticate(String login, String password);

    boolean signUp(String login, String password);

}

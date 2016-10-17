package com.epam.task1.dao;

import com.epam.task1.exception.DaoException;

public interface UserDao extends Dao {

    boolean authenticate(String login, String password) throws DaoException;

}

package com.epam.task1.service;

import com.epam.task1.dao.UserDao;
import com.epam.task1.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean authenticate(String login, String password) {
        return userDao.authenticate(login, password);
    }

}

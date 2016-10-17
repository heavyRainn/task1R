package com.epam.task1.service;

import com.epam.task1.dao.UserDao;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Autowired
    @InjectMocks
    UserService userService;
    @Mock
    UserDao userDao;


    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserServiceLogin() {
        String login = any(String.class);
        String password = any(String.class);

        userService.authenticate(login, password);
        verify(userDao).authenticate(login, password);
    }

}

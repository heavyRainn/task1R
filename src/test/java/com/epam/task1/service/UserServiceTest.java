package com.epam.task1.service;

import com.epam.task1.dao.UserDao;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;


    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        String login = any(String.class);
        String password = any(String.class);

        when(userService.authenticate(login, password)).thenReturn(true);

        when(userDao.authenticate(login, password)).thenReturn(true);
    }

    @Test
    public void testUserServiceLogin() {
        String login = any(String.class);
        String password = any(String.class);

        userService.authenticate(login, password);
        verify(userDao).authenticate(login, password);

        Assert.assertEquals(userService.authenticate(login, password), userDao.authenticate(login, password));
    }

}

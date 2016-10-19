package com.epam.task1.dao.impl;

import com.epam.task1.config.NewspaperConfigTest;
import com.epam.task1.dao.UserDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewspaperConfigTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:db.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:tearDown.xml", type = DatabaseOperation.DELETE_ALL)
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    private static final String LOGIN_EXSISTS = "serega345";
    private static final String PASSWORD_EXSISTS = "123";
    private static final String LOGIN_NOT_EXSISTS = "sere";
    private static final String PASSWORD_NOT_EXSISTS = "1230";
    private static final String LOGIN_SIGN_UP = "Varfalamei";
    private static final String PASSWORD_SIGN_UP = "530";

    @Test
    @Rollback
    public void testAuthenticateReturnTrue() {
        Assert.assertTrue(userDao.authenticate(LOGIN_EXSISTS, PASSWORD_EXSISTS));
    }

    @Test
    @Rollback
    public void testAuthenticateReturnFalse() {
        Assert.assertFalse(userDao.authenticate(LOGIN_NOT_EXSISTS, PASSWORD_NOT_EXSISTS));
    }

    @Test
    @Rollback
    public void testSignUpReturnTrue() {
        Assert.assertTrue(userDao.signUp(LOGIN_SIGN_UP, PASSWORD_SIGN_UP));
    }

}

package com.epam.task1.dao.impl;

import com.epam.task1.dao.UserDao;
import com.epam.task1.dao.connectionpool.DataSource;
import org.apache.log4j.BasicConfigurator;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class UserDaoImplTest {

    @Configuration
    @ComponentScan("com.epam.task1")
    static class ContextConfiguration {
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback
    public void testAuthenticateReturnTrue() {
        String login = "Grek221";
        String password = "1222";

        Assert.assertTrue(userDao.authenticate(login, password));
    }

    @Test
    @Rollback
    public void testAuthenticateReturnFalse() {
        String login = "123";
        String password = "123";

        Assert.assertFalse(userDao.authenticate(login, password));
    }

}

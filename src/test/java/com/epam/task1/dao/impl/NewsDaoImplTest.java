package com.epam.task1.dao.impl;

import com.epam.task1.config.NewspaperConfigTest;
import com.epam.task1.dao.NewsDao;
import com.epam.task1.model.News;
import com.epam.task1.model.Theme;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewspaperConfigTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:db.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:tearDown.xml", type = DatabaseOperation.DELETE_ALL)
public class NewsDaoImplTest {

    @Autowired
    private NewsDao newsDao;

    private static final String NEWS_TITLE = "Solution";
    private static final int NEWS_ID = 501;

    @Test
    @Rollback
    public void testViewAllNews() {
        List<News> news = newsDao.viewAllNews();

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testViewPopularNews() {
        List<News> news = newsDao.viewAllPopularNews();

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testViewAllNewsByTheme() {
        Theme theme = Theme.CRIMINAL;

        List<News> news = newsDao.viewAllNews(theme);

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
        Assert.assertEquals(news.get(0).getTheme(), theme);
    }

    @Test
    @Rollback
    public void testViewASingleNewsById() {
        List<News> news = newsDao.viewASingleNews(NEWS_ID);

        Assert.assertFalse(news.isEmpty());
        Assert.assertEquals(news.get(0).getId(), NEWS_ID);
    }

    @Test
    @Rollback
    public void testViewASingleNewsByTitle() {
        List<News> news = newsDao.viewASingleNews(NEWS_TITLE);

        Assert.assertFalse(news.isEmpty());
        Assert.assertEquals(news.get(0).getMainTitle(), NEWS_TITLE);
    }

}

package com.epam.task1.dao.impl;

import com.epam.task1.dao.NewsDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.model.News;
import com.epam.task1.model.Theme;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class NewsDaoImplTest {

    @Configuration
    @ComponentScan("com.epam.task1")
    static class ContextConfiguration {
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    private NewsDao newsDao;

    @Test
    @Rollback
    public void testViewAllNews() {
        List<News> news;
        news = newsDao.viewAllNews();

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testViewPopularNews() {
        List<News> news;
        news = newsDao.viewAllPopularNews();

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testViewAllNewsByTheme() {
        Theme theme = Theme.FASHION;

        List<News> news;
        news = newsDao.viewAllNews(theme);

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
        Assert.assertEquals(news.get(0).getTheme(), theme);
    }

    @Test
    @Rollback
    public void testViewASingleNewsById() {
        int idNews = 501;

        List<News> news;
        news = newsDao.viewASingleNews(idNews);

        Assert.assertFalse(news.isEmpty());
        Assert.assertEquals(news.get(0).getId(), idNews);
    }

    @Test
    @Rollback
    public void testViewASingleNewsByTitle() {
        String title = "Solution";

        List<News> news;
        news = newsDao.viewASingleNews(title);

        Assert.assertFalse(news.isEmpty());
        Assert.assertEquals(news.get(0).getMainTitle(), title);
    }

}

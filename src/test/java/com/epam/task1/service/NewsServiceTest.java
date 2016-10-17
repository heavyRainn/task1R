package com.epam.task1.service;

import com.epam.task1.dao.NewsDao;
import com.epam.task1.model.*;
import com.epam.task1.service.search.NewsSearchCriteria;
import com.epam.task1.service.search.NewsSearchType;
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

import java.sql.Date;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

    @InjectMocks
    NewsService newsService;

    @Mock
    NewsDao newsDao;

    @Mock
    News news;

    @Mock
    List list;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        when(newsService.viewAllNews()).thenReturn(list);
        when(newsService.viewAllPopularNews()).thenReturn(list);
        when(newsService.addNews(news)).thenReturn(true);
        when(newsService.editNews(news)).thenReturn(true);
        when(newsService.deleteNews(anyInt())).thenReturn(true);
        when(newsService.addComment(anyInt(), anyInt())).thenReturn(true);
        when(newsService.attachTagToNews(anyInt(), anyInt())).thenReturn(true);
        when(newsService.totalCount()).thenReturn(3);

        when(newsDao.viewAllNews()).thenReturn(list);
        when(newsDao.viewAllPopularNews()).thenReturn(list);
        when(newsDao.addNews(news)).thenReturn(true);
        when(newsDao.editNews(news)).thenReturn(true);
        when(newsDao.deleteNews(anyInt())).thenReturn(true);
        when(newsDao.addComment(anyInt(), anyInt())).thenReturn(true);
        when(newsDao.attachTagToNews(anyInt(), anyInt())).thenReturn(true);
        when(newsDao.totalCount()).thenReturn(3);
    }

    @Test
    public void testNewsServiceViewAllNews() {
        newsService.viewAllNews();
        verify(newsDao).viewAllNews();

        Assert.assertEquals(newsService.viewAllNews(), newsDao.viewAllNews());
    }

    @Test
    public void testNewsServiceViewAllPopularNews() {
        newsService.viewAllPopularNews();
        verify(newsDao).viewAllPopularNews();

        Assert.assertEquals(newsService.viewAllPopularNews(), newsDao.viewAllPopularNews());
    }

    @Test
    public void testNewsServiceViewASingleNewsById() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        int intStub = 3;
        searchCriteria.setId(intStub);

        newsService.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(searchCriteria.getId());

        Assert.assertEquals(newsService.viewASingleNews(searchCriteria), newsDao.viewASingleNews(searchCriteria.getId()));
    }


    @Test
    public void testNewsServiceViewASingleNewsByAuthors() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_AUTHOR);
        searchCriteria.setAuthors(list);

        newsService.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(searchCriteria.getAuthors());

        Assert.assertEquals(newsService.viewASingleNews(searchCriteria), newsDao.viewASingleNews(searchCriteria.getId()));
    }

    @Test
    public void testNewsServiceAddNews() {
        newsService.addNews(news);
        verify(newsDao).addNews(news);

        Assert.assertEquals(newsService.addNews(news), newsDao.addNews(news));
    }

    @Test
    public void testNewsServiceEditNews() {
        newsService.editNews(news);
        verify(newsDao).editNews(news);

        Assert.assertEquals(newsService.editNews(news), newsDao.editNews(news));
    }

    @Test
    public void testNewsServiceDeleteNews() {
        int intStub = anyInt();

        newsService.deleteNews(intStub);
        verify(newsDao).deleteNews(intStub);

        Assert.assertEquals(newsService.deleteNews(intStub), newsDao.deleteNews(intStub));
    }

    @Test
    public void testNewsServiceAddComment() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsService.addComment(intStub, intStub1);
        verify(newsDao).addComment(intStub, intStub1);

        Assert.assertEquals(newsService.addComment(intStub, intStub1), newsDao.addComment(intStub, intStub1));
    }

    @Test
    public void testNewsServiceAttachTagToNews() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsService.attachTagToNews(intStub, intStub1);
        verify(newsDao).attachTagToNews(intStub, intStub1);

        Assert.assertEquals(newsService.attachTagToNews(intStub, intStub1), newsDao.attachTagToNews(intStub, intStub1));
    }

    @Test
    public void testNewsServiceTotalCount() {
        newsService.totalCount();
        verify(newsDao).totalCount();

        Assert.assertEquals(newsService.totalCount(), newsDao.totalCount());
    }

}
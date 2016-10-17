package com.epam.task1.service;

import com.epam.task1.dao.NewsDao;
import com.epam.task1.model.Author;
import com.epam.task1.model.Comment;
import com.epam.task1.model.News;
import com.epam.task1.model.Tag;
import com.epam.task1.service.search.NewsSearchCriteria;
import com.epam.task1.service.search.NewsSearchType;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewsServiceTest {

    @Autowired
    @InjectMocks
    NewsService newsService;
    @Mock
    NewsDao newsDao;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNewsServiceViewAllNews() {
        newsService.viewAllNews();
        verify(newsDao).viewAllNews();
    }

    @Test
    public void testNewsServiceViewAllPopularNews() {
        newsService.viewAllPopularNews();
        verify(newsDao).viewAllPopularNews();
    }

    @Test
    public void testNewsServiceViewASingleNewsById() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        int intStub = 3;
        searchCriteria.setId(intStub);

        newsService.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(searchCriteria.getId());
    }

    @Test
    public void testNewsServiceViewASingleNewsByAuthors() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_AUTHOR);
        List<Author> authors = (List<Author>) mock(List.class);
        searchCriteria.setAuthors(authors);

        newsService.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(authors);
    }

    @Test
    public void testNewsServiceAddNews() {
        News news = mock(News.class);

        newsService.addNews(news);
        verify(newsDao).addNews(news);
    }

    @Test
    public void testNewsServiceEditNews() {
        News news = mock(News.class);

        newsService.editNews(news);
        verify(newsDao).editNews(news);
    }

    @Test
    public void testNewsServiceDeleteNews() {
        int intStub = anyInt();

        newsService.deleteNews(intStub);
        verify(newsDao).deleteNews(intStub);
    }

    @Test
    public void testNewsServiceAddComment() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsService.addComment(intStub, intStub1);
        verify(newsDao).addComment(intStub, intStub1);
    }

    @Test
    public void testNewsServiceAttachTagToNews() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsService.attachTagToNews(intStub, intStub1);
        verify(newsDao).attachTagToNews(intStub, intStub1);
    }

    @Test
    public void testNewsServiceTotalCount() {
        newsService.totalCount();
        verify(newsDao).totalCount();
    }

    //@Test
    //public void testNewsServiceTotalCountOnTheme() {
    //   Theme theme = mock(Theme.class);
    //
    //    newsService.totalCount(theme);
    //    verify(newsDao).totalCount(theme);
    //}


}
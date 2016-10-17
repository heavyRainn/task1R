package com.epam.task1.service;

import com.epam.task1.dao.NewsDao;
import com.epam.task1.exception.ServiceException;
import com.epam.task1.model.Comment;
import com.epam.task1.model.News;
import com.epam.task1.model.Tag;
import com.epam.task1.model.Theme;
import com.epam.task1.service.search.NewsSearchCriteria;
import com.epam.task1.service.search.NewsSearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    public List<News> viewAllNews() throws ServiceException {
        return newsDao.viewAllNews();
    }

    public List<News> viewAllNews(Theme theme) throws ServiceException {
        return newsDao.viewAllNews(theme);
    }

    public List<News> viewAllPopularNews() throws ServiceException {
        return newsDao.viewAllPopularNews();
    }

    public List<News> viewASingleNews(NewsSearchCriteria searchCriteria) throws ServiceException {
        NewsSearchType searchType = searchCriteria.getSearchType();
        List<News> news = null;

        if (searchType == NewsSearchType.BY_ID) {
            news = newsDao.viewASingleNews(searchCriteria.getId());
        }
        if (searchType == NewsSearchType.BY_TITLE) {
            news = newsDao.viewASingleNews(searchCriteria.getTitle());
        }
        if (searchType == NewsSearchType.BY_TAGS) {
            news = newsDao.viewASingleNews(searchCriteria.getTags());
        }
        if (searchType == NewsSearchType.BY_AUTHOR) {
            news = newsDao.viewASingleNews(searchCriteria.getAuthors());
        }

        return news;
    }

    public boolean addNews(News news) throws ServiceException {
        return newsDao.addNews(news);
    }

    public boolean editNews(News news) throws ServiceException {
        return newsDao.editNews(news);
    }

    public boolean deleteNews(int id) throws ServiceException {
        return newsDao.deleteNews(id);
    }

    public boolean addComment(int idNews, int idComment) throws ServiceException {
        return newsDao.addComment(idNews, idComment);
    }

    public boolean attachTagToNews(int idNews, int idTag) throws ServiceException {
        return newsDao.attachTagToNews(idNews, idTag);
    }

    public int totalCount() throws ServiceException {
        return newsDao.totalCount();
    }

    public int totalCount(Theme theme) throws ServiceException {
        return newsDao.totalCount(theme);
    }

}

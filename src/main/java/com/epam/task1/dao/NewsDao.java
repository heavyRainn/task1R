package com.epam.task1.dao;

import com.epam.task1.exception.DaoException;
import com.epam.task1.model.*;

import java.util.List;
import java.util.Set;

public interface NewsDao extends Dao {

    List<News> viewAllNews();

    List<News> viewAllNews(Theme theme);

    List<News> viewAllPopularNews();

    List<News> viewASingleNews(int id);

    List<News> viewASingleNews(String title);

    List<News> viewASingleNews(Set<Tag> tags);

    List<News> viewASingleNews(List<Author> authors);

    boolean addNews(News news);

    boolean editNews(News news);

    boolean deleteNews(int id);

    boolean addComment(int idNews, int idComment);

    boolean attachTagToNews(int idNews, int idTag);

    int totalCount();

    int totalCount(Theme theme);
}

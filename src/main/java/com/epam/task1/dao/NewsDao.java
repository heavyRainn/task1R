package com.epam.task1.dao;

import com.epam.task1.exception.DaoException;
import com.epam.task1.model.*;

import java.util.List;
import java.util.Set;

public interface NewsDao extends Dao {

    List<News> viewAllNews() throws DaoException;

    List<News> viewAllNews(Theme theme) throws DaoException;

    List<News> viewAllPopularNews() throws DaoException;

    News viewASingleNews(int id) throws DaoException;

    News viewASingleNews(String title) throws DaoException;

    News viewASingleNews(Set<Tag> tags) throws DaoException;

    News viewASingleNews(List<Author> authors) throws DaoException;

    boolean addNews(News news) throws DaoException;

    boolean editNews(int id, News news) throws DaoException;

    boolean deleteNews(int id) throws DaoException;

    boolean addComment(int idNews, Comment comment) throws DaoException;

    boolean attachTagToNews(int idNews, Tag tag) throws DaoException;

    int totalCount() throws DaoException;

    int totalCount(Theme theme) throws DaoException;
}

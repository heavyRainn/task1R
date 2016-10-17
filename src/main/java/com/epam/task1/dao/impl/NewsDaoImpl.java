package com.epam.task1.dao.impl;

import com.epam.task1.dao.NewsDao;
import com.epam.task1.exception.DaoException;
import com.epam.task1.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Repository
@Transactional
public class NewsDaoImpl implements NewsDao {

    private final static String SQL_GET_ALL_NEWS = "SELECT N_ID,N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO," +
            "N_THEME FROM NEWS";
    private final static String SQL_GET_ALL_NEWS_BY_THEME = "SELECT N_ID,N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT," +
            "N_DATE,N_PHOTO,N_THEME FROM NEWS WHERE N_THEME = ?";
    private final static String SQL_GET_ALL_POPULAR_NEWS = "";
    private final static String SQL_GET_SINGLE_NEWS_BY_ID = "SELECT N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT," +
            "N_DATE,N_PHOTO,N_THEME FROM NEWS WHERE N_ID = ?";
    private final static String SQL_GET_SINGLE_NEWS_BY_TITLE = "SELECT N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT," +
            "N_DATE,N_PHOTO,N_THEME FROM NEWS WHERE N_MAINTITLE = ?";
    private final static String SQL_GET_SINGLE_NEWS_BY_TAGS = "";
    private final static String SQL_GET_SINGLE_NEWS_BY_AUTHORS = "";
    private final static String SQL_CREATE_NEWS = "INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT," +
            "N_DATE,N_PHOTO,N_THEME) VALUES (null,?,?,?,?,?,?)";
    private final static String SQL_EDIT_NEWS = "";
    private final static String SQL_DELETE_NEWS = "";

    public List<News> viewAllNews() throws DaoException {
        List<News> newsList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_NEWS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                News news = new News();

                news.setId(rs.getInt(1));
                news.setMainTitle(rs.getString(2));
                news.setShortTitle(rs.getString(3));
                news.setNewsText(rs.getString(4));
                news.setDate(rs.getDate(5));
                news.setPhoto(rs.getString(6));
                news.setTheme(Theme.valueOf(rs.getString(7).toUpperCase()));

                newsList.add(news);

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return newsList;
    }

    @Override
    public List<News> viewAllNews(Theme theme) throws DaoException {
        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_NEWS_BY_THEME)) {

            ps.setString(1, theme.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                News news = new News();

                news.setId(rs.getInt(1));
                news.setMainTitle(rs.getString(2));
                news.setShortTitle(rs.getString(3));
                news.setNewsText(rs.getString(4));
                news.setDate(rs.getDate(5));
                news.setPhoto(rs.getString(6));
                news.setTheme(Theme.valueOf(rs.getString(7).toUpperCase()));

                newsList.add(news);

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return newsList;
    }

    public List<News> viewAllPopularNews() throws DaoException {
        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_POPULAR_NEWS)) {

            rs = ps.executeQuery();

            while (rs.next()) {

                News news = new News();

                newsList.add(news);

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return newsList;
    }

    public News viewASingleNews(int id) throws DaoException {
        News news = null;
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_ID)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                news = new News();

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return news;
    }

    public News viewASingleNews(String title) throws DaoException {
        News news = null;
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_TITLE)) {

            ps.setString(1, title);

            rs = ps.executeQuery();

            while (rs.next()) {

                news = new News();

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return news;
    }

    public News viewASingleNews(Set<Tag> tags) throws DaoException {
        News news = null;
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_TAGS)) {


            rs = ps.executeQuery();

            while (rs.next()) {

                news = new News();

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return news;
    }

    public News viewASingleNews(List<Author> authors) throws DaoException {
        News news = null;
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_AUTHORS)) {


            rs = ps.executeQuery();

            while (rs.next()) {

                news = new News();

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return news;
    }

    public boolean addNews(News news) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_NEWS)) {

            ps.setString(1, news.getMainTitle());
            ps.setString(2, news.getShortTitle());
            ps.setString(3, news.getNewsText());
            ps.setDate(4, news.getDate());
            ps.setString(5, news.getPhoto());
            ps.setString(6, news.getTheme().toString());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean editNews(int id, News news) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_EDIT_NEWS)) {


            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    public boolean deleteNews(int id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_NEWS)) {


            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    public boolean addComment(int idNews, Comment comment) throws DaoException {
        return false;
    }

    public boolean attachTagToNews(int idNews, Tag tag) throws DaoException {
        return false;
    }

    public int totalCount() throws DaoException {
        return viewAllNews().size() + 1;
    }

    public int totalCount(Theme theme) throws DaoException {
        return viewAllNews(theme).size();
    }
}

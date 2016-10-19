package com.epam.task1.dao.impl;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.exception.DaoException;
import com.epam.task1.model.Author;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AuthorDaoImpl implements CrudDao<Author> {

    @Autowired
    private DataSource dataSource;

    private final static String SQL_GET_ALL_AUTHORS = "SELECT ATR_ID,ATR_NAME,ATR_SURNAME FROM AUTHORS";
    private final static String SQL_GET_ALL_AUTHORS_OF_ONE_NEWS = "SELECT AUTHORS.ATR_ID,AUTHORS.ATR_NAME,AUTHORS.ATR_SURNAME" +
            " FROM AUTHORS INNER JOIN NEWS_HAVE_AUTHORS ON AUTHORS.ATR_ID = NEWS_HAVE_AUTHORS.ATR_ID WHERE " +
            " NEWS_HAVE_AUTHORS.N_ID = ?";
    private final static String SQL_CREATE_AUTHOR = "INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (null,?,?)";
    private final static String SQL_UPDATE_AUTHOR = "UPDATE AUTHORS SET ATR_NAME = ?, ATR_SURNAME = ? WHERE ATR_ID = ?";
    private final static String SQL_DELETE_AUTHOR = "DELETE FROM AUTHORS WHERE ATR_ID = ?";

    public boolean create(Author author) throws DaoException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_AUTHOR)) {

            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Author> read() throws DaoException {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_AUTHORS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Author author = new Author(rs.getString(2), rs.getString(3));
                author.setId(rs.getInt(1));

                authors.add(author);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return authors;
    }

    @Override
    public List<Author> read(int idNews) throws DaoException {
        List<Author> authors = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_AUTHORS_OF_ONE_NEWS)) {

            ps.setInt(1, idNews);

            rs = ps.executeQuery();

            while (rs.next()) {

                Author author = new Author(rs.getString(2), rs.getString(3));
                author.setId(rs.getInt(1));

                authors.add(author);

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

        return authors;
    }


    public boolean update(Author author) throws DaoException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_AUTHOR)) {

            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setInt(3, author.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean delete(int id) throws DaoException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_AUTHOR)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }
}

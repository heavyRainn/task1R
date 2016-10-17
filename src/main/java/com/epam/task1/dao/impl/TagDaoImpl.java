package com.epam.task1.dao.impl;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.exception.DaoException;
import com.epam.task1.model.Author;
import com.epam.task1.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class TagDaoImpl implements CrudDao<Tag> {

    @Autowired
    private DataSource dataSource;

    private final static String SQL_CREATE_TAG = "INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (null, ?)";
    private final static String SQL_GET_ALL_TAGS = "SELECT TG_ID,TG_MESSAGE FROM TAGS";
    private final static String SQL_GET_NEWS_TAGS = "SELECT TAGS.TG_ID,TAGS.TG_MESSAGE FROM TAGS INNER JOIN NEWS_HAVE_TAGS " +
            "ON TAGS.TG_ID = NEWS_HAVE_TAGS.TG_ID WHERE NEWS_HAVE_TAGS.N_ID = ?";
    private final static String SQL_UPDATE_TAG = "UPDATE TAGS SET TG_MESSAGE = ? WHERE TG_ID = ?";
    private final static String SQL_DELETE_TAG = "DELETE FROM TAGS WHERE TG_ID = ?";

    public boolean create(Tag tag) throws DaoException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_TAG)) {

            ps.setString(1, tag.getText());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Tag> read() throws DaoException {
        List<Tag> tags = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_TAGS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Tag tag = new Tag(rs.getString(2));
                tag.setId(rs.getInt(1));

                tags.add(tag);

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return tags;
    }

    @Override
    public List<Tag> read(int idNews) throws DaoException {
        List<Tag> tags = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_NEWS_TAGS)) {

            ps.setInt(1, idNews);

            rs = ps.executeQuery();

            while (rs.next()) {

                Tag tag = new Tag(rs.getString(2));
                tag.setId(rs.getInt(1));

                tags.add(tag);

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

        return tags;
    }

    public boolean update(Tag tag) throws DaoException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TAG)) {

            ps.setString(1, tag.getText());
            ps.setInt(2, tag.getId());

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
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_TAG)) {

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

package com.epam.task1.dao.impl;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.exception.DaoException;
import com.epam.task1.model.Author;
import com.epam.task1.model.Comment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component(value = "comment")
@Repository
@Transactional
public class CommentDaoImpl implements CrudDao<Comment> {

    private final static String SQL_CREATE_COMMENT = "INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR) " +
            "VALUES (null,?,?,?)";
    private final static String SQL_GET_ALL_COMMENTS = "SELECT COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR FROM COMMENTS";
    private final static String SQL_GET_ALL_COMMENTS_OF_ONE_NEWS = "SELECT COMMENTS.COM_ID, COMMENTS.COM_MESSAGE," +
            "COMMENTS.COM_DATE,COMMENTS.COM_ID_AUTHOR FROM COMMENTS INNER JOIN NEWS_HAVE_COMMENTS ON COMMENTS.COM_ID" +
            "= NEWS_HAVE_COMMENTS.COM_ID WHERE NEWS_HAVE_COMMENTS.N_ID = ?";
    private final static String SQL_UPDATE_COMMENT = "UPDATE COMMENTS SET COM_MESSAGE = ?, COM_DATE = SYSDATE," +
            "COM_ID_AUTHOR = ? WHERE COM_ID = ?";
    private final static String SQL_DELETE_COMMENT = "DELETE FROM COMMENTS WHERE COM_ID = ?";

    public boolean create(Comment comment) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_COMMENT)) {

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            ps.setString(1, comment.getText());
            ps.setDate(2, sqlDate);
            ps.setInt(3, comment.getUserId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Comment> read() throws DaoException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_COMMENTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Comment comment = new Comment(rs.getString(2), rs.getDate(3));
                comment.setId(rs.getInt(1));
                comment.setUserId(rs.getInt(4));

                comments.add(comment);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return comments;
    }

    @Override
    public List<Comment> read(int idNews) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_COMMENTS_OF_ONE_NEWS)) {

            ps.setInt(1, idNews);

            rs = ps.executeQuery();

            while (rs.next()) {

                Comment comment = new Comment(rs.getString(2), rs.getDate(3));
                comment.setId(rs.getInt(1));
                comment.setUserId(rs.getInt(4));

                comments.add(comment);

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

        return comments;
    }

    public boolean update(Comment comment) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_COMMENT)) {

            ps.setString(1, comment.getText());
            ps.setInt(2, comment.getUserId());
            ps.setInt(3, comment.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean delete(int id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_COMMENT)) {

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

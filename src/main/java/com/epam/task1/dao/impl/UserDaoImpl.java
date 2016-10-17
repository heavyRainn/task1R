package com.epam.task1.dao.impl;

import com.epam.task1.dao.UserDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.exception.DaoException;
import com.epam.task1.model.Author;
import com.epam.task1.model.User;
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
public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSource dataSource;

    private static final String AUTHENTICATE = "SELECT UR_ID,UR_LOGIN,UR_PASSWORD FROM USERS WHERE UR_LOGIN=(?) " +
            "and UR_PASSWORD=(?)";

    public boolean authenticate(String login, String password) throws DaoException {
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(AUTHENTICATE)) {

            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {

                if (login.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                    return true;
                }

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
        return false;
    }

}

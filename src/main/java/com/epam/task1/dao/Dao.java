package com.epam.task1.dao;

import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public interface Dao {

    default Connection getConnection() throws DaoException {
        return DataSource.getConnection();
    }

}

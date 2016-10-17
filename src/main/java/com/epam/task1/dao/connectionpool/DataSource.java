package com.epam.task1.dao.connectionpool;

import com.epam.task1.exception.DaoException;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource {

    @Autowired
    private BasicDataSource ds;

    public Connection getConnection() throws DaoException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}

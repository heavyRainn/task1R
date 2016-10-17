package com.epam.task1.dao.connectionpool;

import com.epam.task1.exception.DaoException;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource {

    private static BasicDataSource ds;

    public static Connection getConnection() throws DaoException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Autowired(required = true)
    public void setDs(BasicDataSource ds) {
        DataSource.ds = ds;
    }

}

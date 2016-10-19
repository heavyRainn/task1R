package com.epam.task1.exception;

/**
 * Created by Andrei_Fiodarau on 10/18/2016.
 */
public class DataSourceException extends DaoException {

    public DataSourceException(Throwable exception) {
        super(exception);
    }

    public DataSourceException(String string) {
        super(string);
    }
}

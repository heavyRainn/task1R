package com.epam.task1.exception;

public class DaoException extends NewspaperException {

    private static final long serialVersionUID = 2768520820248976282L;

    public DaoException(Throwable exception) {
        super(exception);
    }

    public DaoException(String string) {
        super(string);
    }
}

package com.epam.task1.exception;

public class DaoException extends RuntimeException{

    private static final long serialVersionUID = 2768520820248976282L;

    public DaoException(Throwable exception) {
        super(exception);
    }

    public DaoException(String string) {
        super(string);
    }
}

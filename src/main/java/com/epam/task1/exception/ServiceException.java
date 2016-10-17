package com.epam.task1.exception;

public class ServiceException extends NewspaperException {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(Exception e) {
        super(e);
    }

}

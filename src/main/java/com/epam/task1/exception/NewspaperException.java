package com.epam.task1.exception;

/**
 * Created by Andrei_Fiodarau on 10/17/2016.
 */
public class NewspaperException extends RuntimeException {

    public NewspaperException(Throwable exception) {
        super(exception);
    }

    public NewspaperException(String string) {
        super(string);
    }

    public NewspaperException(String message, Exception e) {
        super(message, e);
    }
}

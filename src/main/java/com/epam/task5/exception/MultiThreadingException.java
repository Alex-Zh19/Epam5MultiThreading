package com.epam.task5.exception;

public class MultiThreadingException extends Exception {
    public MultiThreadingException() {
    }

    public MultiThreadingException(String message) {
        super(message);
    }

    public MultiThreadingException(Throwable cause) {
        super(cause);
    }

    public MultiThreadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

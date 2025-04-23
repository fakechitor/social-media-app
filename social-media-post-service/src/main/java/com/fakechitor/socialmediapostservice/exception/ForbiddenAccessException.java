package com.fakechitor.socialmediapostservice.exception;

public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException() {
        super();
    }

    public ForbiddenAccessException(String message) {
        super(message);
    }

    public ForbiddenAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenAccessException(Throwable cause) {
        super(cause);
    }

}

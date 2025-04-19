package com.taskello.domain.exception;

// todo: handle
public class InvalidTaskException extends RuntimeException {

    public InvalidTaskException(final String message) {
        super(message);
    }

}

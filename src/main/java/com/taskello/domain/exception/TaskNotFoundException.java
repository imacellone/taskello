package com.taskello.domain.exception;

// todo: handle
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(final Long taskId) {
        super("Task with Id: " + taskId + " not found.");
    }

}

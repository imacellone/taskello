package com.taskello.domain.exception;

// todo: handle
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long taskId) {
        super("TaskEntity with Id: " + taskId + " not found.");
    }

}

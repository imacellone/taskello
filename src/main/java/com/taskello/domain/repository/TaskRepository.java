package com.taskello.domain.repository;

import com.taskello.domain.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    Task save(final Task task);
    Task findById(final Long taskId);
}

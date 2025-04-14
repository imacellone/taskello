package com.taskello.domain.service;

import com.taskello.domain.entity.Task;
import com.taskello.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class TaskCrudService {

    private final TaskRepository taskRepository;

    List<Task> findAll() {
        return taskRepository.findAll();
    }
}

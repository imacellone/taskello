package com.taskello.domain.service;

import com.taskello.domain.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskCrudService taskCrudService;

    public List<Task> findAll() {
        return taskCrudService.findAll();
    }

    @Transactional
    public Task save(final Task newTask) {
        return taskCrudService.save(newTask);
    }
}

package com.taskello.domain.service;

import com.taskello.domain.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskCrudService taskCrudService;

    public List<Task> findAll() {
        return taskCrudService.findAll();
    }
}

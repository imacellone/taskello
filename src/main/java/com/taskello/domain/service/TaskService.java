package com.taskello.domain.service;

import com.taskello.domain.model.Task;
import com.taskello.domain.model.command.TaskTogglesCommand;
import com.taskello.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public Task save(final Task newTaskEntity) {
        return taskRepository.save(newTaskEntity);
    }

    @Transactional
    public Task updateToggles(final TaskTogglesCommand taskTogglesCommand, final Long taskId) {
        Task task = taskRepository.findById(taskId);
        task.apply(taskTogglesCommand);
        return taskRepository.save(task);
    }

}

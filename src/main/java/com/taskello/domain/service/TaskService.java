package com.taskello.domain.service;

import com.taskello.domain.exception.TaskNotFoundException;
import com.taskello.domain.model.Task;
import com.taskello.domain.model.command.TaskTogglesCommand;
import com.taskello.domain.repository.TaskRepository;
import lombok.NonNull;
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

    public Task findById(@NonNull final Long taskId) {
        final Task task = taskRepository.findById(taskId);
        requireTaskExists(task, taskId);
        return task;
    }

    @Transactional
    public Task save(@NonNull final Task newTask) {
        return taskRepository.save(newTask);
    }

    @Transactional
    public Task updateToggles(@NonNull final TaskTogglesCommand taskTogglesCommand, @NonNull final Long taskId) {
        final Task task = findById(taskId);
        task.apply(taskTogglesCommand);
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteById(@NonNull final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private void requireTaskExists(final Task task, final Long taskId) {
        if (task == null) {
            throw new TaskNotFoundException(taskId);
        }
    }

}

package com.taskello.persistence.repository;

import com.taskello.domain.exception.TaskNotFoundException;
import com.taskello.domain.model.Task;
import com.taskello.domain.repository.TaskRepository;
import com.taskello.persistence.entity.TaskEntity;
import com.taskello.common.mapper.BiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class TaskRepositoryImpl implements TaskRepository {

    private final SpringDataJpaTaskRepository repositoryDelegate;
    private final BiMapper<Task, TaskEntity> taskEntityMapper;

    @Override
    public List<Task> findAll() {
        final List<TaskEntity> taskEntities = repositoryDelegate.findAll();
        return taskEntityMapper.toInternals(taskEntities);
    }

    @Override
    public Task save(final Task task) {
        final TaskEntity taskEntity = taskEntityMapper.toExternal(task);
        final TaskEntity persistedTaskEntity = repositoryDelegate.save(taskEntity);
        return taskEntityMapper.toInternal(persistedTaskEntity);
    }

    @Override
    public Task findById(final Long taskId) {
        return repositoryDelegate
                .findById(taskId)
                .map(taskEntityMapper::toInternal)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}

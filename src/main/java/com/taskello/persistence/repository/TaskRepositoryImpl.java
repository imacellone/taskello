package com.taskello.persistence.repository;

import com.taskello.common.mapper.BiMapper;
import com.taskello.domain.exception.TaskNotFoundException;
import com.taskello.domain.model.Task;
import com.taskello.domain.repository.TaskRepository;
import com.taskello.persistence.entity.TaskEntity;
import lombok.NonNull;
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
    public Task findById(@NonNull final Long taskId) {
        final TaskEntity taskEntity = findEntityByIdOrThrow(taskId);
        return taskEntityMapper.toInternal(taskEntity);
    }

    @Override
    public Task save(@NonNull final Task task) {
        return task.getId() == null ? create(task) : update(task);
    }

    @Override
    public void deleteById(@NonNull final Long taskId) {
        requireTaskExists(taskId);
        repositoryDelegate.deleteById(taskId);
    }

    private Task create(final Task task) {
        final TaskEntity taskEntity = taskEntityMapper.toExternal(task);
        final TaskEntity persistedTaskEntity = repositoryDelegate.save(taskEntity);
        return taskEntityMapper.toInternal(persistedTaskEntity);
    }

    private Task update(final Task task) {
        final TaskEntity taskEntity = findEntityByIdOrThrow(task.getId());
        taskEntity.updateFrom(task);
        return taskEntityMapper.toInternal(taskEntity);
    }

    private TaskEntity findEntityByIdOrThrow(@NonNull final Long taskId) {
        return repositoryDelegate.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private void requireTaskExists(final Long taskId) {
        if (!repositoryDelegate.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
    }
}

package com.taskello.api.v1.mapper;

import com.taskello.api.v1.model.input.TaskInput;
import com.taskello.api.v1.model.output.TaskOutput;
import com.taskello.domain.entity.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper modelMapper;

    public Task toEntity(final TaskInput taskInput) {
        return modelMapper.map(taskInput, Task.class);
    }

    public List<TaskOutput> toOutput(final List<Task> allTasks) {
        return Optional.ofNullable(allTasks)
                .orElseGet(List::of)
                .stream()
                .map(this::toOutput)
                .toList();
    }

    public TaskOutput toOutput(final Task taskEntity) {
        return modelMapper.map(taskEntity, TaskOutput.class);
    }
}

package com.taskello.api.v1.resource;

import com.taskello.api.ApiPaths;
import com.taskello.api.v1.mapper.TaskMapper;
import com.taskello.api.v1.model.input.TaskInput;
import com.taskello.api.v1.model.output.TaskOutput;
import com.taskello.domain.entity.Task;
import com.taskello.domain.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.V1_TASKS)
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskOutput> findAll() {
        List<Task> allTasks = taskService.findAll();
        return taskMapper.toOutput(allTasks);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutput create(@RequestBody @Valid final TaskInput taskInput) {
        Task newTask = taskMapper.toEntity(taskInput);
        Task persistedTask = taskService.save(newTask);
        return taskMapper.toOutput(persistedTask);
    }


}

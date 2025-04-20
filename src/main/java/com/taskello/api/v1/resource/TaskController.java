package com.taskello.api.v1.resource;

import com.taskello.api.ApiPaths;
import com.taskello.api.v1.model.input.TaskInput;
import com.taskello.api.v1.model.input.TaskTogglesInput;
import com.taskello.api.v1.model.output.TaskOutput;
import com.taskello.common.mapper.BiMapper;
import com.taskello.domain.model.Task;
import com.taskello.domain.model.command.TaskTogglesCommand;
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
    private final BiMapper<Task, TaskOutput> taskOutputMapper;
    private final BiMapper<Task, TaskInput> taskInputMapper;
    private final BiMapper<TaskTogglesCommand, TaskTogglesInput> taskTogglesInputMapper;

    @GetMapping
    public List<TaskOutput> findAll() {
        final List<Task> allTasks = taskService.findAll();
        return taskOutputMapper.toExternals(allTasks);
    }

    @GetMapping("/{taskId}")
    public TaskOutput findById(@PathVariable final Long taskId) {
        final Task task = taskService.findById(taskId);
        return taskOutputMapper.toExternal(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutput create(@RequestBody @Valid final TaskInput taskInput) {
        final Task newTask = taskInputMapper.toInternal(taskInput);
        final Task persistedTask = taskService.save(newTask);
        return taskOutputMapper.toExternal(persistedTask);
    }

    @PatchMapping("/{taskId}/toggles")
    public TaskOutput updateToggles(@PathVariable final Long taskId,
                                    @RequestBody @Valid final TaskTogglesInput taskTogglesInput) {
        final TaskTogglesCommand taskTogglesCommand = taskTogglesInputMapper.toInternal(taskTogglesInput);
        final Task persistedTask = taskService.updateToggles(taskTogglesCommand, taskId);
        return taskOutputMapper.toExternal(persistedTask);
    }

    @PutMapping("/{taskId}")
    public TaskOutput update(@PathVariable final Long taskId,
                             @RequestBody @Valid final TaskInput taskInput) {
        final Task task = taskInputMapper.toInternal(taskInput);
        task.setId(taskId);
        final Task updatedTask = taskService.save(task);
        return taskOutputMapper.toExternal(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteById(@PathVariable final Long taskId) {
        taskService.deleteById(taskId);
    }

}

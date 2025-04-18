package com.taskello.api.v1.resource;

import com.taskello.api.ApiPaths;
import com.taskello.api.v1.model.input.TaskInput;
import com.taskello.api.v1.model.input.TaskTogglesInput;
import com.taskello.api.v1.model.output.TaskOutput;
import com.taskello.domain.model.Task;
import com.taskello.domain.model.command.TaskTogglesCommand;
import com.taskello.domain.service.TaskService;
import com.taskello.common.mapper.BiMapper;
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
        List<Task> allTaskEntities = taskService.findAll();
        return taskOutputMapper.toExternals(allTaskEntities);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutput create(@RequestBody @Valid final TaskInput taskInput) {
        Task newTask = taskInputMapper.toInternal(taskInput);
        Task persistedTask = taskService.save(newTask);
        return taskOutputMapper.toExternal(persistedTask);
    }

    @PatchMapping("/{taskId}/toggles")
    public TaskOutput updateToggles(@PathVariable final Long taskId,
                                    @RequestBody @Valid final TaskTogglesInput taskTogglesInput) {
        TaskTogglesCommand taskTogglesCommand = taskTogglesInputMapper.toInternal(taskTogglesInput);
        Task persistedTaskEntity = taskService.updateToggles(taskTogglesCommand, taskId);
        return taskOutputMapper.toExternal(persistedTaskEntity);
    }

}

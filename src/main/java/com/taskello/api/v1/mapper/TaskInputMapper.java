package com.taskello.api.v1.mapper;

import com.taskello.api.v1.model.input.TaskInput;
import com.taskello.domain.model.Task;
import com.taskello.common.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class TaskInputMapper extends AbstractMapper<Task, TaskInput> {

    TaskInputMapper(ModelMapper modelMapper) {
        super(Task.class, TaskInput.class, modelMapper);
    }

}

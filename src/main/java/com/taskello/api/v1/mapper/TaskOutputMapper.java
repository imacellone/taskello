package com.taskello.api.v1.mapper;

import com.taskello.api.v1.model.output.TaskOutput;
import com.taskello.domain.model.Task;
import com.taskello.common.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class TaskOutputMapper extends AbstractMapper<Task, TaskOutput> {

    TaskOutputMapper(ModelMapper modelMapper) {
        super(Task.class, TaskOutput.class, modelMapper);
    }

}

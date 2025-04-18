package com.taskello.persistence.mapper;

import com.taskello.domain.model.Task;
import com.taskello.persistence.entity.TaskEntity;
import com.taskello.common.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class TaskEntityMapper extends AbstractMapper<Task, TaskEntity> {

    TaskEntityMapper(ModelMapper modelMapper) {
        super(Task.class, TaskEntity.class, modelMapper);
    }
}

package com.taskello.api.v1.mapper;

import com.taskello.api.v1.model.input.TaskTogglesInput;
import com.taskello.domain.model.command.TaskTogglesCommand;
import com.taskello.common.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class TaskToggleInputMapper extends AbstractMapper<TaskTogglesCommand, TaskTogglesInput> {

    TaskToggleInputMapper(ModelMapper modelMapper) {
        super(TaskTogglesCommand.class, TaskTogglesInput.class, modelMapper);
    }

}

package com.taskello.domain.model.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TaskTogglesCommand {

    private Boolean done;

}

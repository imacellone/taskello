package com.taskello.api.v1.model.input;

import com.taskello.common.validation.AtLeastOneFieldPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AtLeastOneFieldPresent
public class TaskTogglesInput {
    private Boolean done;
}

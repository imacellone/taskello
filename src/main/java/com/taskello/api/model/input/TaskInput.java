package com.taskello.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class TaskInput {

    @NotBlank
    @Size(max = 500, min = 1)
    // TODO: Create a a custom validation message
    private String title;

    @Size(max = 163830)
    // TODO: Create a a custom validation message
    private String description;

    @NotNull
    // TODO: Create a a custom validation message
    private Boolean done;

    // TODO: Only future? If so, validate with a custom message
    private OffsetDateTime deadline;

}

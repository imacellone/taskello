package com.taskello.api.v1.model.output;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskOutput {
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private OffsetDateTime deadline;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}

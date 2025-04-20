package com.taskello.domain.model;

import com.taskello.domain.model.command.TaskTogglesCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean done;
    private OffsetDateTime deadline;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public void apply(final TaskTogglesCommand taskTogglesCommand) {
        this.done = Optional.ofNullable(taskTogglesCommand)
                .map(TaskTogglesCommand::getDone)
                .orElseThrow(() -> new IllegalArgumentException("No toggles present"));
    }
}

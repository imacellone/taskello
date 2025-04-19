package com.taskello.persistence.entity;

import com.taskello.domain.exception.InvalidTaskException;
import com.taskello.domain.model.Task;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.taskello.common.util.DateTimeUtils.nowUtcTruncatedToMinute;
import static com.taskello.common.util.DateTimeUtils.truncateToMinutesOrNullAndConvertToUtc;
import static java.util.function.Predicate.not;

@Entity
@Getter
@Table(name = "task")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "is_done")
    private boolean done;

    private String title;
    private String description;
    private OffsetDateTime deadline;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public void setTitle(final String title) {
        final String newTitle = normalizeStringOrNull(title);
        if (newTitle == null) {
            throw new InvalidTaskException("Task provided for update has invalid title");
        }
        this.title = newTitle;
    }

    public void setDescription(final String description) {
        this.description = normalizeStringOrNull(description);
    }

    public void setDeadline(final OffsetDateTime deadline) {
        this.deadline = truncateToMinutesOrNullAndConvertToUtc(deadline);
    }

    public void updateFrom(final Task task) {
        requireSameIdentityAs(task);
        setTitle(task.getTitle());
        setDeadline(task.getDeadline());
        setDone(task.isDone());
        setDescription(task.getDescription());
    }

    private void requireSameIdentityAs(final Task task) {
        final Long otherId = Optional.ofNullable(task)
                .map(Task::getId)
                .orElseThrow(() -> new InvalidTaskException("Update Task - null or missing ID"));

        if (!otherId.equals(this.id)) {
            throw new InvalidTaskException("Update Task - ID mismatch");
        }
    }

    private static String normalizeStringOrNull(final String string) {
        return Optional.ofNullable(string)
                .map(String::trim)
                .filter(not(String::isBlank))
                .orElse(null);
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = nowUtcTruncatedToMinute();
        this.updatedAt = createdAt;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = nowUtcTruncatedToMinute();
    }
}

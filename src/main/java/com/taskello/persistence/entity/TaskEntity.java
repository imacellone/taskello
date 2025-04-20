package com.taskello.persistence.entity;

import com.taskello.domain.exception.InvalidTaskException;
import com.taskello.domain.model.Task;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
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

    /**
     * Sets the title of the {@link TaskEntity}
     * <p>
     * Leading and trailing whitespace will be removed. If the title is blank,
     * the assignment will be rejected with a domain exception.
     * </p>
     *
     * @param title the raw title input
     * @throws InvalidTaskException if the trimmed title is null or empty
     */
    public void setTitle(@NonNull final String title) {
        final String newTitle = normalizeStringOrNull(title);
        if (newTitle == null) {
            throw new InvalidTaskException("Invalid Task Title");
        }
        this.title = newTitle;
    }

    /**
     * Sets the description of the {@link TaskEntity}
     * <p>
     * Leading and trailing whitespace will be removed.
     * </p>
     *
     * @param description the raw description input
     */
    public void setDescription(final String description) {
        this.description = normalizeStringOrNull(description);
    }

    /**
     * Sets the task's deadline after normalizing the given value.
     *
     * <p>
     * The input {@link OffsetDateTime} is truncated to the nearest minute and converted to UTC.
     * If {@code null} is provided, the deadline will also be set to {@code null}.
     * </p>
     *
     * @param deadline the deadline to set, may be {@code null}
     */
    public void setDeadline(final OffsetDateTime deadline) {
        this.deadline = truncateToMinutesOrNullAndConvertToUtc(deadline);
    }

    /**
     * Updates this task entity's mutable fields using values from the given domain {@link Task} object.
     *
     * <p>
     * The update will only proceed if the provided task is not {@code null} and has the same identifier
     * as this entity. The following fields are updated: title, deadline, done status, and description.
     * Values are normalized as necessary (e.g., strings are trimmed, deadlines are converted to UTC).
     * </p>
     *
     * <p>
     * This method delegates to individual setter methods, which may throw exceptions
     * if the input values violate domain constraints.
     * </p>
     *
     * @param task the domain task to copy values from; must not be {@code null} and must have the same ID
     * @throws InvalidTaskException if the given task is {@code null}, has a {@code null} ID,
     *                              the ID does not match this entity's ID,
     *                              or any of the delegated setter methods reject the input as invalid
     */
    public void updateFrom(@NonNull final Task task) {
        requireSameIdentityAs(task);
        setTitle(task.getTitle());
        setDeadline(task.getDeadline());
        setDone(task.isDone());
        setDescription(task.getDescription());
    }

    private void requireSameIdentityAs(final Task task) {
        if (!this.getId().equals(task.getId())) {
            throw new InvalidTaskException("Update Task - ID mismatch");
        }
    }

    private static String normalizeStringOrNull(final String string) {
        return Optional.ofNullable(string).map(String::trim).filter(not(String::isBlank)).orElse(null);
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

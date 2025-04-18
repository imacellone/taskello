package com.taskello.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

import static com.taskello.common.util.DateTimeUtils.nowUtcTruncatedToMinute;
import static com.taskello.common.util.DateTimeUtils.truncateToMinutesOrNullAndConvertToUtc;

@Entity
@Getter
@Setter
@Table(name = "task")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private String description;

    @Column(name = "is_done")
    private boolean done;

    private OffsetDateTime deadline;

    // todo: try to encapsulate this again
    private OffsetDateTime createdAt;

    @Setter(AccessLevel.NONE)
    private OffsetDateTime updatedAt;

    public void setDeadline(final OffsetDateTime deadline) {
        this.deadline = truncateToMinutesOrNullAndConvertToUtc(deadline);
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

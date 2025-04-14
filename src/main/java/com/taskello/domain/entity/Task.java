package com.taskello.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

import static com.taskello.util.DateTimeUtils.nowUtcTruncatedToMinute;
import static com.taskello.util.DateTimeUtils.truncateToMinutesOrNullAndConvertToUtc;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private String description;

    @Column(name = "is_done")
    private boolean done;

    private OffsetDateTime deadline;

    @Setter(AccessLevel.NONE)
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

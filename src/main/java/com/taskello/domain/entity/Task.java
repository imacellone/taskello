package com.taskello.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

import static com.taskello.util.DateTimeUtils.nowTruncatedToMinute;
import static com.taskello.util.DateTimeUtils.truncateToMinutesOrNull;

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
        this.deadline = truncateToMinutesOrNull(deadline);
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = nowTruncatedToMinute();
        this.updatedAt = createdAt;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = nowTruncatedToMinute();
    }

}

package com.taskello.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 500, min = 1)
    // TODO: Move validations to inputDTO
    private String title;

    @Size(max = 163830)
    // TODO: Move validations to inputDTO
    private String description;

    @Column(name = "is_done")
    private boolean done;

    // TODO: Only future? If so, create validation in the inputDTO
    private OffsetDateTime deadline;

    @NotNull
    @Setter(AccessLevel.NONE)
    private OffsetDateTime createdAt;

    @NotNull
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

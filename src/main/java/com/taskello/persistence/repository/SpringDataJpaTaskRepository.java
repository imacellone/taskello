package com.taskello.persistence.repository;

import com.taskello.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataJpaTaskRepository extends JpaRepository<TaskEntity, Long> {
}

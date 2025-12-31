package com.qoutraining.taskmanagement.repository;

import com.qoutraining.taskmanagement.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findTasksByUserEmail(String email, Pageable pageable);

    Optional<Task> findByIdAndUserEmail(Long id, String email);
}

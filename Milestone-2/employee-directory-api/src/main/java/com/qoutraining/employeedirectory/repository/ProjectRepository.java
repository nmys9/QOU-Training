package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

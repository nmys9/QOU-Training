package com.qoutraining.taskmanagement.repository;

import com.qoutraining.taskmanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

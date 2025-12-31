package com.qoutraining.taskmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.taskmanagement.model.enums.Priority;
import com.qoutraining.taskmanagement.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "TASK_SEQ", allocationSize = 1)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(name = "CREATED_AT",updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_STATUS",nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}

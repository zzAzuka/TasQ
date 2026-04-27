package com.zenloww.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    enum Status{
        PENDING,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer taskid;

    private String name;
    private String description;
    private Status status;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectid")
    private Project project;

    public Task(String name, String description, Status status, LocalDateTime deadline, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name=name;
        this.description=description;
        this.status=status;
        this.deadline=deadline;
        this.createdAt= createdAt;
        this.updatedAt=updatedAt;
    }
}

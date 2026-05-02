package com.zenloww.repository;

import com.zenloww.common.Status;
import com.zenloww.entity.Project;
import com.zenloww.entity.Task;
import com.zenloww.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Page<Task> findByStatus(Status status, Pageable pageable);
    Page<Task> findByUser(Optional<User> user, Pageable pageable);
    Page<Task> findByProject(Optional<Project> projectid, Pageable pageable);
}

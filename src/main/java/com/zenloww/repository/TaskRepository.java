package com.zenloww.repository;

import com.zenloww.common.Status;
import com.zenloww.entity.Project;
import com.zenloww.entity.Task;
import com.zenloww.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(Status status);
    List<Task> findByUser(Optional<User> user);
    List<Task> findByProject(Optional<Project> projectid);
}

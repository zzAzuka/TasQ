package com.zenloww.service;

import com.zenloww.common.Status;
import com.zenloww.dto.TaskDto;
import com.zenloww.entity.Project;
import com.zenloww.entity.Task;
import com.zenloww.entity.User;
import com.zenloww.mapper.TaskMapper;
import com.zenloww.repository.ProjectRepository;
import com.zenloww.repository.TaskRepository;
import com.zenloww.repository.UserProjectRepository;
import com.zenloww.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private UserProjectRepository userProjectRepository;

    public Page<TaskDto> getAllTasks(Pageable pageable) {
        Page<Task> taskList = taskRepository.findAll(pageable);
        return taskList.map(TaskMapper::mapToTaskDto);
    }

    public Page<TaskDto> getTaskByUser(Integer userid, Pageable pageable) {
        Optional<User> currentUser = userRepository.findById(userid);
        Page<Task> taskList = taskRepository.findByUser(currentUser, pageable);
        return taskList.map(TaskMapper::mapToTaskDto);

    }

    public Page<TaskDto> getTaskByProject(Integer projectid, Pageable pageable) {
        Optional<Project> currentProject = projectRepository.findById(projectid);
        Page<Task> taskList = taskRepository.findByProject(currentProject, pageable);
        return taskList.map(TaskMapper::mapToTaskDto);
    }

    public Page<TaskDto> getTaskByStatus(Status status, Pageable pageable) {
        Page<Task> taskList = taskRepository.findByStatus(status, pageable);
        return taskList.map(TaskMapper::mapToTaskDto);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task newTask = TaskMapper.mapToTask(taskDto);
        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setUpdatedAt(LocalDateTime.now());
        Task createdTask = taskRepository.save(newTask);
        return (TaskMapper.mapToTaskDto(createdTask));
    }

    public TaskDto updateTask(Integer id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setName(taskDto.getName());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setDeadline(taskDto.getDeadline());
        existingTask.setUpdatedAt(LocalDateTime.now());

        Task updated = taskRepository.save(existingTask);
        return TaskMapper.mapToTaskDto(updated);
    }

    public void removeTask(Integer taskid) {
        taskRepository.deleteById(taskid);
        return;
    }

    public TaskDto assignTask(Integer taskid, Integer userid, Integer projectid) {
        Task existingTask = taskRepository.findById(taskid)
                .orElseThrow(() -> new RuntimeException("Task Not Found"));
        User existingUser = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        Project existingProject = projectRepository.findById(projectid)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (userProjectRepository.existsByProjectidAndUserid(projectid, userid)) {
            existingTask.setUser(existingUser);
            existingTask.setProject(existingProject);
            existingTask.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(existingTask);

            return TaskMapper.mapToTaskDto(existingTask);
        }
        else {
            throw new RuntimeException("Error, User and Project don't belong!");
        }
    }
}

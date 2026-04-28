package com.zenloww.service;

import com.zenloww.dto.TaskDto;
import com.zenloww.entity.Project;
import com.zenloww.entity.Task;
import com.zenloww.entity.User;
import com.zenloww.mapper.TaskMapper;
import com.zenloww.mapper.UserMapper;
import com.zenloww.repository.ProjectRepository;
import com.zenloww.repository.TaskRepository;
import com.zenloww.repository.UserProjectRepository;
import com.zenloww.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private UserProjectRepository userProjectRepository;

    public List<TaskDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskList.stream()
                .map(TaskMapper::mapToTaskDto)
                .toList();
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task newTask = TaskMapper.mapToTask(taskDto);
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

package com.zenloww.controller;

import com.zenloww.dto.TaskDto;
import com.zenloww.dto.UserDto;
import com.zenloww.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks/")
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllUTasks() {
        List<TaskDto> taskList = taskService.getAllTasks();
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<> (createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskid}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Integer taskid, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskid, taskDto);
        return new ResponseEntity<> (updatedTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskid}")
    public ResponseEntity<String> removeTask(@PathVariable Integer taskid) {
        taskService.removeTask(taskid);
        return new ResponseEntity<> ("Removed Task!", HttpStatus.FOUND);
    }
}

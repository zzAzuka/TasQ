package com.zenloww.controller;

import com.zenloww.common.Status;
import com.zenloww.dto.TaskDto;
import com.zenloww.dto.UserDto;
import com.zenloww.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> taskList = taskService.getAllTasks(pageable);
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<Page<TaskDto>> getTaskByUser(@RequestParam(name="userid", required=false) Integer userid,
                                                       @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> taskList = taskService.getTaskByUser(userid, pageable);
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @GetMapping("/projects")
    public ResponseEntity<Page<TaskDto>> getTaskByProject(@RequestParam(name="projectid", required=false) Integer projectid,
                                                          @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> taskList = taskService.getTaskByProject(projectid, pageable);
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @GetMapping("/status")
    public ResponseEntity<Page<TaskDto>> getTaskByStatus(@RequestParam(name="status", required = false) Status status,
                                                         @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> taskList = taskService.getTaskByStatus(status, pageable);
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<> (createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskid}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Integer taskid, @Valid @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskid, taskDto);
        return new ResponseEntity<> (updatedTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskid}")
    public ResponseEntity<String> removeTask(@PathVariable Integer taskid) {
        taskService.removeTask(taskid);
        return new ResponseEntity<> ("Removed Task!", HttpStatus.FOUND);
    }

    @PutMapping("/{taskid}/{userid}/{projectid}")
    public ResponseEntity<TaskDto> assignUserProjectTask(@PathVariable Integer taskid, @PathVariable Integer userid, @PathVariable Integer projectid) {
        TaskDto assignedTask = taskService.assignTask(taskid, userid, projectid);
        return new ResponseEntity<> (assignedTask, HttpStatus.CREATED);
    }
}

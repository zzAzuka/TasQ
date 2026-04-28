package com.zenloww.mapper;

import com.zenloww.dto.TaskDto;
import com.zenloww.entity.Task;

public class TaskMapper {
    public static TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
            task.getName(),
            task.getDescription(),
            task.getStatus(),
            task.getDeadline()
        );
    }

    public static Task mapToTask(TaskDto taskDto) {
        return new Task(
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getStatus(),
                taskDto.getDeadline()
        );
    }
}

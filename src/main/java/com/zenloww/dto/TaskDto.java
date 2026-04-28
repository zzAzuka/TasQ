package com.zenloww.dto;

import com.zenloww.common.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @NotBlank(message = "Task Name cannot be blank")
    private String name;

    @Size(max = 1000, message = "Task Description should be up to 1000 characters")
    private String description;

    private Status status;
    private LocalDateTime deadline;
}

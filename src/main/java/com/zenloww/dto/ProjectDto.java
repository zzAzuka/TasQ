package com.zenloww.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    @NotBlank(message = "Project Name cannot be blank")
    private String projectname;

    @Size(max = 500, message = "Project Description should be up to 500 characters")
    private String projectdescription;
}

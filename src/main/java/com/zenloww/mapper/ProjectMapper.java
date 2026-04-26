package com.zenloww.mapper;

import com.zenloww.dto.ProjectDto;
import com.zenloww.dto.UserDto;
import com.zenloww.entity.Project;
import com.zenloww.entity.User;

public class ProjectMapper {
    public static ProjectDto mapToProjectDto(Project project) {
        return new ProjectDto(
                project.getProjectname(),
                project.getProjectdescription()
        );
    }

    public static Project mapToProject(ProjectDto projectDto) {
        return new Project(
                projectDto.getProjectname(),
                projectDto.getProjectdescription()
        );
    }
}

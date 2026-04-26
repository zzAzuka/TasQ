package com.zenloww.service;

import com.zenloww.dto.ProjectDto;
import com.zenloww.entity.Project;
import com.zenloww.mapper.ProjectMapper;
import com.zenloww.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectDto addProject(ProjectDto projectDto) {
        Project mappedProject = ProjectMapper.mapToProject(projectDto);
        Project savedProject = projectRepository.save(mappedProject);
        return ProjectMapper.mapToProjectDto(savedProject);
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        return projectList.stream()
                .map(ProjectMapper::mapToProjectDto)
                .toList();
    }
}

package com.zenloww.controller;

import com.zenloww.dto.ProjectDto;
import com.zenloww.entity.UserProjectMembership;
import com.zenloww.service.ProjectService;
import com.zenloww.service.UserProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/projects/")
public class ProjectController {
    private ProjectService projectService;
    private UserProjectService userProjectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> allProjects = projectService.getAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.addProject(projectDto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PostMapping("/{projectid}/users/{userid}/")
    public ResponseEntity<UserProjectMembership> addProjectUser(@PathVariable Integer projectid, @PathVariable Integer userid) {
        System.out.println(projectid + "and" + userid);
        UserProjectMembership createdMembership = userProjectService.addUserToProject(projectid, userid);
        return new ResponseEntity<>(createdMembership, HttpStatus.CREATED);
    }

}

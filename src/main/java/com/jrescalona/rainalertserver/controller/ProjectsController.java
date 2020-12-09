package com.jrescalona.rainalertserver.controller;

import com.jrescalona.rainalertserver.model.Project;
import com.jrescalona.rainalertserver.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectsController {
    ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectsService.getAllProjects();
    }

    @GetMapping(path = "{id}")
    public Project getProjectById(@PathVariable("id") UUID id) {
        return projectsService.getProjectById(id);
    }

    @PostMapping(path = "{id}")
    public void updateProjectById(@PathVariable("id") UUID id, @RequestBody Project project) {
        projectsService.updateProjectById(id, project);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProjectById(@PathVariable("id") UUID id) {
        projectsService.deleteProjectById(id);
    }
}

package com.jrescalona.rainalertserver.controller;

import com.jrescalona.rainalertserver.model.Project;
import com.jrescalona.rainalertserver.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/projects")
@RestController
public class ProjectsController {
    private final ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PostMapping
    public void addProject(@RequestBody Project project) {
        projectsService.addProject(project);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectsService.getAllProjects();
    }

//    @GetMapping(path = "{projectId}")
//    public Project getProjectById(@PathVariable("projectId") UUID id) {
//        return projectsService.getProjectById(id);
//    }

    @GetMapping(path = "{userId}")
    public List<Project> getAllProjectsById(@PathVariable("userId") UUID id) {
        System.out.println(id.toString());
        return projectsService.getAllProjectByUserId(id);
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

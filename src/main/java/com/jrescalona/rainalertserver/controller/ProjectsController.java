package com.jrescalona.rainalertserver.controller;

import com.jrescalona.rainalertserver.model.Project;
import com.jrescalona.rainalertserver.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/projects/{userId}")
public class ProjectsController {
    private final ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    /**
     * Add New Project
     * @param project new project to be added
     */
    @PostMapping
    public void addProject(@RequestBody Project project) {
        projectsService.addProject(project);
    }

    /**
     * Get single project
     * @param id projectId
     * @return Project
     */
    @GetMapping(path = "{projectId}")
    public Project getProjectById(@PathVariable("projectId") UUID id) {
        return projectsService.getProjectById(id);
    }

    /**
     * Get all projects associated with a user
     * @param id user's id
     * @return List<Project>
     */
    @GetMapping
    public List<Project> getAllProjectsByUserId(@PathVariable("userId") UUID id) {
        return projectsService.getAllProjectByUserId(id);
    }

    /**
     * Update a single project
     * @param id id of project to be updated
     * @param project updated project
     */
    @PostMapping(path = "{projectId}")
    public void updateProjectById(@PathVariable("projectId") UUID id, @RequestBody Project project) {
        projectsService.updateProjectById(id, project);
    }

    /**
     * Delete a single project
     * @param id id of project to be deleted
     */
    @DeleteMapping(path = "{projectId}")
    public void deleteProjectById(@PathVariable("projectId") UUID id) {
        projectsService.deleteProjectById(id);
    }
}

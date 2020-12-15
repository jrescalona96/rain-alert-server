package com.jrescalona.rainalertserver.service;

import com.jrescalona.rainalertserver.doa.InMemoryProjectsDoa;
import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectsServiceTest {
    Project p1 = new Project(
            null,
            "Project #1",
            "This is project for testing.",
            new Address(
                    UUID.randomUUID(),
                    "123 Testing St.",
                    "Apt. 001",
                    "Barstool",
                    "CA",
                    "98654",
                    new Location(
                            UUID.randomUUID(),
                            "LOX",
                            801,
                            45,
                            34.6758,
                            -117.3721 )));

    Project p2 = new Project(
            null,
            "Project #2",
            "This the second project for testing.",
            new Address(
                    UUID.randomUUID(),
                    "456 Testing Ave.",
                    "Apt. 002",
                    "Table",
                    "CO",
                    "98654",
                    new Location(
                            UUID.randomUUID(),
                            "LOX",
                            801,
                            45,
                            34.6758,
                            -117.3721)));
    ProjectsService projectsService;

    @BeforeEach
    void setUp() {
        this.projectsService = new ProjectsService(new InMemoryProjectsDoa());
    }

    @Test
    void addProjectShouldAddOneNewProjectWillSucceed() {
        assertEquals(1, projectsService.getAllProjects().size());
    }

    @Test
    void addProjectShouldHaveCorrectProject() {
        projectsService.addProject(p1);
        assertEquals(p1, projectsService.getAllProjects().get(0));
    }

    @Test
    void addProjectShouldAddAnotherProject() {
        projectsService.addProject(p1);
        projectsService.addProject(p2);
        assertEquals(2, projectsService.getAllProjects().size());
    }

    @Test
    void getAllProjects() {
        projectsService.addProject(p1);
        projectsService.addProject(p2);
        assertEquals(2, projectsService.getAllProjects().size());
    }

    @Test
    void getProjectByIdShouldReturnProject() {
        projectsService.addProject(p1);
        projectsService.addProject(p2);
        List<Project> projects  = projectsService.getAllProjects();
        UUID p2ID = projects.get(1).getId();

        Project res = projectsService.getProjectById(p2ID);

        assertEquals(p2.getId(), res.getId());
    }

    @Test
    void getProjectByIdShouldReturnNull() {
        projectsService.addProject(p1);
        projectsService.addProject(p2);
        assertNull(projectsService.getProjectById(UUID.randomUUID()));
    }

    @Test
    void getProjectByIdFromEmptyRepoShouldReturnNull() {
        projectsService.addProject(p1);
        projectsService.addProject(p2);
        assertNull(projectsService.getProjectById(UUID.randomUUID()));
    }

    @Test
    void deleteProjectByIdShouldSucceed() {

        projectsService.addProject(p1);
        projectsService.addProject(p2);
        List<Project> projects  = projectsService.getAllProjects();
        UUID id = projects.get(0).getId();

        int res = projectsService.deleteProjectById(id);

        assertEquals(0, res);
        assertEquals(1, projectsService.getAllProjects().size());
    }

    @Test
    void deleteProjectByIdShouldFail() {
        projectsService.addProject(p1);
        projectsService.addProject(p2);
        List<Project> projects  = projectsService.getAllProjects();

        int res = projectsService.deleteProjectById(UUID.randomUUID());

        assertEquals(1, res);
        assertEquals(2, projectsService.getAllProjects().size());
    }

    @Test
    void updateProjectById() {
        projectsService.addProject(p1);
        UUID id = projectsService.getAllProjects().get(0).getId();

        projectsService.updateProjectById(id, p2);

        assertEquals(p2.getName(), projectsService.getAllProjects().get(0).getName());
        assertEquals(p2.getDescription(), projectsService.getAllProjects().get(0).getDescription());
        assertEquals(p2.getAddress(), projectsService.getAllProjects().get(0).getAddress());
    }
}
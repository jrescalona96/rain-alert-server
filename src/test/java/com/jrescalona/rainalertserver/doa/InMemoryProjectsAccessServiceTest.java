package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.jrescalona.rainalertserver.model.USState.*;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryProjectsAccessServiceTest {
    InMemoryProjectsDoa repo;
    UUID p1ID = UUID.randomUUID();
    UUID p2ID = UUID.randomUUID();
    UUID randomId = UUID.randomUUID();

    Project p1 = new Project(
            p1ID,
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
                            "801",
                            "45",
                            34.6758,
                            -117.3721
                    )));

    Project p2 = new Project(
            p2ID,
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
                            "801",
                            "45",
                            34.6758,
                            -117.3721
                    )));

    @BeforeEach
    void setUp() {
        repo = new InMemoryProjectsDoa();
    }

    @Test
    void insertProjectWithNoUUIDShouldReturn1() {
        repo = new InMemoryProjectsDoa();
        int res = repo.insertProject(p1);
        assertEquals(0,res);
        assertNotNull(repo.selectProjectById(p1ID));
    }

    @Test
    void insertProjectWithNoUUIDShouldHaveOneProject() {
        repo.insertProject(p1);
        List<Project> projects = repo.selectAllProjects();
        assertEquals(1, projects.size());
    }


    @Test
    void insertProjectWithNoUUIDShouldHaveTwoProjects() {
        repo.insertProject(p1);
        repo.insertProject(p2);
        List<Project> projects = repo.selectAllProjects();
        assertEquals(2, projects.size());
    }


    @Test
    void insertProjectWithNoUUIDShouldHaveValidId() {
        repo.insertProject(p1);
        List<Project> projects =  repo.selectAllProjects();
        assertNotNull(projects.get(0).getId());
    }

    @Test
    void insertProjectWithUUIDShouldReturn1() {
        repo = new InMemoryProjectsDoa();
        int res = repo.insertProject(p1ID, p1);
        assertEquals(0,res);
        assertNotNull(repo.selectProjectById(p1ID));
    }

    @Test
    void insertProjectWithUUIDShouldHaveOneProject() {
        repo.insertProject(p1ID, p1);
        List<Project> projects = repo.selectAllProjects();
        assertEquals(1, projects.size());
    }

    @Test
    void insertProjectWithUUIDShouldHaveTwoProjects() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        List<Project> projects = repo.selectAllProjects();
        assertEquals(2, projects.size());
    }


    @Test
    void insertProjectWithUUIDShouldHaveCorrectId() {
        repo.insertProject(p1ID, p1);
        List<Project> projects =  repo.selectAllProjects();
        assertEquals(p1ID, projects.get(0).getId());
    }

    @Test
    void selectProjectByIdShouldContainProject() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        Optional<Project> result = repo.selectProjectById(p1ID);
        assertNotNull(result.get());
    }

    @Test
    void selectProjectByIdShouldReturnCorrectProject() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        Optional<Project> result = repo.selectProjectById(p1ID);
        assertEquals(p1, result.get());
    }

    @Test
    void selectProjectByIdShouldNotContainProject() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        Optional<Project> result = repo.selectProjectById(randomId);
        assertTrue(result.isEmpty());
    }

    @Test
    void selectAllProjects() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        assertEquals(2, repo.selectAllProjects().size());
    }

    @Test
    void updateProjectByIdShouldSucceed() {
        repo.insertProject(p1ID, p1);
        p2.setId(null);
        repo.updateProjectById(p1ID, p2);
        assertEquals(p2.getName(), repo.selectAllProjects().get(0).getName());
        assertEquals(p2.getDescription(), repo.selectAllProjects().get(0).getDescription());
        assertEquals(p2.getAddress(), repo.selectAllProjects().get(0).getAddress());
    }

    @Test
    void deleteProjectByIdShouldSucceed() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        repo.deleteProjectById(p2ID);
        assertEquals(1, repo.selectAllProjects().size());
    }

    @Test
    void deleteProjectByIdShouldDeleteCorrectItem() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        repo.deleteProjectById(p2ID);
        assertEquals(p1ID, repo.selectAllProjects().get(0).getId());
    }

    @Test
    void deleteProjectByIdShouldFail() {
        repo.insertProject(p1ID, p1);
        repo.insertProject(p2ID, p2);
        repo.deleteProjectById(randomId);
        assertEquals(2, repo.selectAllProjects().size());
    }
}
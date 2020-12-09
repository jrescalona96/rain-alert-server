package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.jrescalona.rainalertserver.model.USState.CA;
import static com.jrescalona.rainalertserver.model.USState.CO;

@Repository("InMemoryProjects")
public class InMemoryProjectsDoa implements IProjectsDoa {

    // TESTING ONLY
    Project p1 = new Project(
            null,
            "Project #1",
            "This is project for testing.",
            new Address(
                    UUID.randomUUID(),
                    "123 Testing St.",
                    "Apt. 001",
                    "Barstool",
                    CA,
                    "98654",
                    new Location(
                            UUID.randomUUID(),
                            "LOX",
                            "801",
                            "45",
                            "34.6758",
                            "-117.3721" )));

    Project p2 = new Project(
            null,
            "Project #2",
            "This the second project for testing.",
            new Address(
                    UUID.randomUUID(),
                    "456 Testing Ave.",
                    "Apt. 002",
                    "Table",
                    CO,
                    "98654",
                    new Location(
                            UUID.randomUUID(),
                            "LOX",
                            "801",
                            "45",
                            "34.6758",
                            "-117.3721")));

    private final List<Project> DB;

    public InMemoryProjectsDoa() {
        DB = new ArrayList<>();
        insertProject(p1);
        insertProject(p2);
    }

    /**
     * Creates a random UUID
     * then inserts new project using UUID
     * @param project Project
     * @return 0
     */
    @Override
    public int insertProject(Project project) {
        UUID projectId = UUID.randomUUID();
        insertProject(projectId, project);
        return 0;
    }

    /**
     * Appends project to projects
     * @param id UUID
     * @param project Project
     * @return 0
     */
    @Override
    public int insertProject(UUID id, Project project) {
        project.setId(id);
        // updateAddress
        DB.add(project);
        return 0;
    }

    /**
     * Finds project with UUID id
     * @param id UUID
     * @return Project with the UUID id or null if not found
     */
    @Override
    public Optional<Project> selectProjectById(UUID id) {
         return DB.stream()
                 .filter(p -> p.getId().equals(id))
                 .findFirst();
    }

    /**
     * @return All projects
     */
    @Override
    public List<Project> selectAllProjects() {
        return DB;
    }

    /**
     * Invokes selectProjectById()
     * Replaces project with new Project with same id if found
     * @param id UUID
     * @param projectUpdate Project
     * @return 0 if successful, 1 otherwise
     */
    @Override
    public int updateProjectById(UUID id, Project projectUpdate) {
         Optional<Project> optionalProject =  selectProjectById(id);
         return optionalProject
                .map(found -> {
                    int indexFound = DB.indexOf(found);
                    projectUpdate.setId(id);
                    DB.set(indexFound, projectUpdate);
                    return 0;
                })
                .orElse(1);
    }

    /**
     * Invokes selectProjectById()
     * Deletes for database if found
     * @param id UUID
     * @return 0 if successful, 1 otherwise
     */
    @Override
    public int deleteProjectById(UUID id) {
        Optional<Project> optionalProject = selectProjectById(id);

        if(optionalProject.isEmpty()) {
            return 1;
        }

        DB.remove(optionalProject.get());
        return 0;
    }
}
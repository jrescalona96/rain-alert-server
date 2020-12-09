package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("InMemoryProjects")
public class InMemoryProjectsDoa implements IProjectsDoa {

    private final List<Project> DB;

    public InMemoryProjectsDoa() {
        DB = new ArrayList<>();
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
     * Creates Ids for Address & Location
     * Appends project to projects
     * @param projectId UUID
     * @param project Project
     * @return 0
     */
    @Override
    public int insertProject(UUID projectId, Project project) {
        project.setId(projectId);
        project.getAddress().setId(UUID.randomUUID());
        project.getAddress().getLocation().setId(UUID.randomUUID());
        DB.add(project);
        return 0;
    }

    /**
     * Finds project with UUID id
     * @param projectId UUID
     * @return Project with the UUID id or null if not found
     */
    @Override
    public Optional<Project> selectProjectById(UUID projectId) {
         return DB.stream()
                 .filter(p -> p.getId().equals(projectId))
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
     * @param projectId UUID
     * @param projectUpdate Project
     * @return 0 if successful, 1 otherwise
     */
    @Override
    public int updateProjectById(UUID projectId, Project projectUpdate) {
         Optional<Project> optionalProject =  selectProjectById(projectId);
         return optionalProject
                .map(found -> {
                    int indexFound = DB.indexOf(found);
                    projectUpdate.setId(projectId);
                    DB.set(indexFound, projectUpdate);
                    return 0;
                })
                .orElse(1);
    }

    /**
     * Invokes selectProjectById()
     * Deletes for database if found
     * @param projectId UUID
     * @return 0 if successful, 1 otherwise
     */
    @Override
    public int deleteProjectById(UUID projectId) {
        Optional<Project> optionalProject = selectProjectById(projectId);

        if(optionalProject.isEmpty()) {
            return 1;
        }

        DB.remove(optionalProject.get());
        return 0;
    }
}
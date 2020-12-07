package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("InMemoryProjects")
public class InMemoryProjectsAccessService implements IProjectsDoa {

    private final List<Project> DB = new ArrayList<>();

    /**
     * Creates a random UUID
     * then inserts new project using UUID
     * @param project Project
     * @return 0
     */
    @Override
    public int insertProject(Project project) {
        UUID id = UUID.randomUUID();
        insertProject(id, project);
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
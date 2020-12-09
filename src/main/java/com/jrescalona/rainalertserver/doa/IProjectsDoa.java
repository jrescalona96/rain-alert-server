package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectsDoa {
    /**
     * Creates a random UUID
     * then inserts new project using UUID
     * Invokes overloaded method
     * @param project Project
     * @return 0 if successful, 1 otherwise
     */
    default int insertProject(Project project) {
        UUID projectId = UUID.randomUUID();
        return insertProject(projectId, project);
    }

    int insertProject(UUID projectId, Project project);
    Optional<Project> selectProjectById(UUID projectId);
    List<Project> selectAllProjects();
    int updateProjectById(UUID projectId,Project project);
    int deleteProjectById(UUID projectId);
}

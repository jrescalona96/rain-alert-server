package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectsDao {
    /**
     * Creates a random UUID
     * then inserts new project using UUID
     * Invokes overloaded method
     * @param project Project
     * @return 0 if successful, 1 otherwise
     */
    default void insertProject(Project project) {
        UUID projectId = UUID.randomUUID();
        insertProject(projectId, project);
    }

    void insertProject(UUID projectId, Project project);
    Optional<Project> selectProjectById(UUID projectId);
    List<Project> selectAllProjects();
    List<Project> selectAllProjectsByUserId(UUID userId);
    int updateProjectById(UUID projectId,Project project);
    int deleteProjectById(UUID projectId);
}

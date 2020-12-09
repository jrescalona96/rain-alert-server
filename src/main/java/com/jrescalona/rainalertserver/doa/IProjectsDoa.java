package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectsDoa {
    int insertProject(Project project);
    int insertProject(UUID projectId, Project project);
    Optional<Project> selectProjectById(UUID projectId);
    List<Project> selectAllProjects();
    int updateProjectById(UUID projectId,Project project);
    int deleteProjectById(UUID projectId);
}

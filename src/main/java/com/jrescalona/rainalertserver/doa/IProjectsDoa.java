package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectsDoa {
    int insertProject(Project project);
    int insertProject(UUID id, Project project);
    Optional<Project> selectProjectById(UUID id);
    List<Project> selectAllProjects();
    int updateProjectById(UUID id,Project project);
    int deleteProjectById(UUID id);
}

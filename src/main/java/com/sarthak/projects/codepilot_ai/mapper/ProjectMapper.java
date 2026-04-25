package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.project.ProjectResponse;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectSummaryResponse;
import com.sarthak.projects.codepilot_ai.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);
}


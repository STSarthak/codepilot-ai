package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.auth.UserProfileResponse;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectResponse;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectSummaryResponse;
import com.sarthak.projects.codepilot_ai.entity.Project;
import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-29T03:54:26+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponse toProjectResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = project.getId();
        name = project.getName();
        createdAt = project.getCreatedAt();
        updatedAt = project.getUpdatedAt();

        UserProfileResponse owner = null;

        ProjectResponse projectResponse = new ProjectResponse( id, name, createdAt, updatedAt, owner );

        return projectResponse;
    }

    @Override
    public ProjectSummaryResponse toProjectSummaryResponse(Project project, ProjectRole role) {
        if ( project == null && role == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        if ( project != null ) {
            id = project.getId();
            name = project.getName();
            createdAt = project.getCreatedAt();
            updatedAt = project.getUpdatedAt();
        }
        ProjectRole role1 = null;
        role1 = role;

        ProjectSummaryResponse projectSummaryResponse = new ProjectSummaryResponse( id, name, createdAt, updatedAt, role1 );

        return projectSummaryResponse;
    }

    @Override
    public List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectSummaryResponse> list = new ArrayList<ProjectSummaryResponse>( projects.size() );
        for ( Project project : projects ) {
            list.add( projectToProjectSummaryResponse( project ) );
        }

        return list;
    }

    protected ProjectSummaryResponse projectToProjectSummaryResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = project.getId();
        name = project.getName();
        createdAt = project.getCreatedAt();
        updatedAt = project.getUpdatedAt();

        ProjectRole role = null;

        ProjectSummaryResponse projectSummaryResponse = new ProjectSummaryResponse( id, name, createdAt, updatedAt, role );

        return projectSummaryResponse;
    }
}

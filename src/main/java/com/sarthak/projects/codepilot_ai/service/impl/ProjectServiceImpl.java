package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.project.ProjectRequest;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectResponse;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectSummaryResponse;
import com.sarthak.projects.codepilot_ai.entity.Project;
import com.sarthak.projects.codepilot_ai.entity.User;
import com.sarthak.projects.codepilot_ai.repository.ProjectRepository;
import com.sarthak.projects.codepilot_ai.repository.UserRepository;
import com.sarthak.projects.codepilot_ai.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.sarthak.projects.codepilot_ai.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponse> getUserProject(Long userId) {
        return projectRepository.findAllAccessibleProjectsByUser(userId)
                .stream()
                .map(projectMapper::toProjectSummaryResponse)
                .toList();
    }

    @Override
    public ProjectResponse getProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id,userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                        .name(request.name())
                        .owner(owner)
                        .isPublic(false)
                        .build();

        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {

        Project project = getAccessibleProjectById(id,userId);

        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    public Project getAccessibleProjectById(Long id, Long userId){
        return projectRepository.findUserProjectById(userId,id).orElseThrow();
    }
}

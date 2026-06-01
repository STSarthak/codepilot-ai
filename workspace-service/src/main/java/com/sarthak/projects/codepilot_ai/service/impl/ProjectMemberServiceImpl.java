package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.member.InviteMemberRequest;
import com.sarthak.projects.codepilot_ai.dto.member.MemberResponse;
import com.sarthak.projects.codepilot_ai.dto.member.UpdateMemberRoleRequest;
import com.sarthak.projects.codepilot_ai.client.AccountClient;
import com.sarthak.projects.codepilot_ai.dto.UserDto;
import com.sarthak.projects.codepilot_ai.entity.Project;
import com.sarthak.projects.codepilot_ai.entity.ProjectMember;
import com.sarthak.projects.codepilot_ai.entity.ProjectMemberId;
import com.sarthak.projects.codepilot_ai.mapper.ProjectMemberMapper;
import com.sarthak.projects.codepilot_ai.repository.ProjectMemberRepository;
import com.sarthak.projects.codepilot_ai.repository.ProjectRepository;
import com.sarthak.projects.codepilot_ai.security.AuthUtil;
import com.sarthak.projects.codepilot_ai.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    AuthUtil authUtil;
    AccountClient accountClient;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        Project project = getAccessibleProjectById(projectId, userId);

        return projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(this::toMemberResponse)
                        .toList();
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        UserDto invitee = accountClient.getUserByEmail(request.username()).orElseThrow();

        if(invitee.id().equals(userId)){
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.id());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite same person");
        }

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return toMemberResponse(projectMember, invitee);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return toMemberResponse(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void removeProjectMember(Long projectId, Long memberId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("No such member found");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }

    private MemberResponse toMemberResponse(ProjectMember projectMember) {
        UserDto user = accountClient.getUserById(projectMember.getId().getUserId());
        return toMemberResponse(projectMember, user);
    }

    private MemberResponse toMemberResponse(ProjectMember projectMember, UserDto user) {
        return new MemberResponse(
                user.id(),
                user.username(),
                user.name(),
                projectMember.getProjectRole(),
                projectMember.getInvitedAt()
        );
    }
}

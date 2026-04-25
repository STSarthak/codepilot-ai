package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.member.InviteMemberRequest;
import com.sarthak.projects.codepilot_ai.dto.member.MemberResponse;
import com.sarthak.projects.codepilot_ai.dto.member.UpdateMemberRoleRequest;
import com.sarthak.projects.codepilot_ai.entity.Project;
import com.sarthak.projects.codepilot_ai.entity.ProjectMember;
import com.sarthak.projects.codepilot_ai.entity.ProjectMemberId;
import com.sarthak.projects.codepilot_ai.entity.User;
import com.sarthak.projects.codepilot_ai.mapper.ProjectMemberMapper;
import com.sarthak.projects.codepilot_ai.repository.ProjectMemberRepository;
import com.sarthak.projects.codepilot_ai.repository.ProjectRepository;
import com.sarthak.projects.codepilot_ai.repository.UserRepository;
import com.sarthak.projects.codepilot_ai.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        List<MemberResponse> memberResponsesList = new ArrayList<>();
        memberResponsesList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponsesList.addAll(
                projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromMember)
                        .toList());

        return memberResponsesList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite same person");
        }

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invtedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId,Long userId, UpdateMemberRoleRequest request) {
        Project project = getAccessibleProjectById(projectId,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("No such member found");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findUserProjectById(userId, id).orElseThrow();
    }
}

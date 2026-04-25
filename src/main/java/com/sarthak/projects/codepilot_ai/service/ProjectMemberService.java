package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.member.InviteMemberRequest;
import com.sarthak.projects.codepilot_ai.dto.member.MemberResponse;
import com.sarthak.projects.codepilot_ai.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, Long userId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId, Long userId);
}

package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.member.InviteMemberRequest;
import com.sarthak.projects.codepilot_ai.dto.member.MemberResponse;
import com.sarthak.projects.codepilot_ai.dto.member.UpdateMemberRoleRequest;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMember(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    MemberResponse deleteProjectMember(Long projectId, Long memberId);
}

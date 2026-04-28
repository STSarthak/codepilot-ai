package com.sarthak.projects.codepilot_ai.controller;

import com.sarthak.projects.codepilot_ai.dto.member.InviteMemberRequest;
import com.sarthak.projects.codepilot_ai.dto.member.MemberResponse;
import com.sarthak.projects.codepilot_ai.dto.member.UpdateMemberRoleRequest;
import com.sarthak.projects.codepilot_ai.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMember(@PathVariable Long projectId){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody @Valid InviteMemberRequest request
    ){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request, userId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @RequestBody @Valid UpdateMemberRoleRequest request,
            @PathVariable Long memberId
    ){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, userId, request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeProjectMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){
        Long userId = 1L;
        projectMemberService.removeProjectMember(projectId, memberId, userId);
        return ResponseEntity.noContent().build();
    }
}

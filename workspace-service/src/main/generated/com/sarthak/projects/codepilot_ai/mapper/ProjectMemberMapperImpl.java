package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.member.MemberResponse;
import com.sarthak.projects.codepilot_ai.entity.ProjectMember;
import com.sarthak.projects.codepilot_ai.entity.ProjectMemberId;
import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-30T02:34:33+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ProjectMemberMapperImpl implements ProjectMemberMapper {

    @Override
    public MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember) {
        if ( projectMember == null ) {
            return null;
        }

        Long userId = null;
        ProjectRole role = null;
        Instant invitedAt = null;

        userId = projectMemberIdUserId( projectMember );
        role = projectMember.getProjectRole();
        invitedAt = projectMember.getInvitedAt();

        String username = null;
        String name = null;

        MemberResponse memberResponse = new MemberResponse( userId, username, name, role, invitedAt );

        return memberResponse;
    }

    private Long projectMemberIdUserId(ProjectMember projectMember) {
        ProjectMemberId id = projectMember.getId();
        if ( id == null ) {
            return null;
        }
        return id.getUserId();
    }
}

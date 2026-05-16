package com.sarthak.projects.codepilot_ai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.sarthak.projects.codepilot_ai.enums.ProjectPermission.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(VIEW,EDIT,DELETE,VIEW_MEMBERS),
    VIEWER(VIEW,VIEW_MEMBERS),
    OWNER(VIEW,EDIT,DELETE,MANAGE_MEMBERS,VIEW_MEMBERS);

    ProjectRole(ProjectPermission... permissions){
        this.permissions = Set.of(permissions);
    }

    public final Set<ProjectPermission> permissions;
}

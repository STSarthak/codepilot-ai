package com.sarthak.projects.codepilot_ai.security;

import com.sarthak.projects.codepilot_ai.client.WorkspaceClient;
import com.sarthak.projects.codepilot_ai.enums.ProjectPermission;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
@Slf4j
public class SecurityExpressions {

    private final WorkspaceClient workspaceClient;

    private boolean hasPermission(Long projectId, ProjectPermission projectPermission) {
        try {
            return workspaceClient.checkPermission(projectId, projectPermission);
        } catch (FeignException.Unauthorized e) {
            throw new CredentialsExpiredException("JWT token is expired or invalid");
        } catch (FeignException e) {
            log.error("Workspace permission check failed: {}", e.getMessage());
            return false;
        }
    }

    public boolean canViewProject(Long projectId) {
        return hasPermission(projectId, ProjectPermission.VIEW);
    }

    public boolean canEditProject(Long projectId) {
        return hasPermission(projectId, ProjectPermission.EDIT);
    }
}

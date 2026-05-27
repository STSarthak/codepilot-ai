package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.deploy.DeployResponse;

public interface DeploymentService {

    DeployResponse deploy(Long projectId);
}

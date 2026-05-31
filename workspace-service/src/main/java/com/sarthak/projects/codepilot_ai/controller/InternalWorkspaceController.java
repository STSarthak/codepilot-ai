package com.sarthak.projects.codepilot_ai.controller;

import com.sarthak.projects.codepilot_ai.dto.FileTreeDto;
import com.sarthak.projects.codepilot_ai.enums.ProjectPermission;
import com.sarthak.projects.codepilot_ai.service.FileService;
import com.sarthak.projects.codepilot_ai.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/v1")
@RequiredArgsConstructor
public class InternalWorkspaceController {

    private final ProjectService projectService;
    private final FileService fileService;

    @GetMapping("/projects/{projectId}/files/tree")
    public FileTreeDto getFileTree(@PathVariable Long projectId) {
        return new FileTreeDto(fileService.getFileTree(projectId).files());
    }

    @GetMapping("/projects/{projectId}/files/content")
    public String getFileContent(@PathVariable Long projectId, @RequestParam String path) {
        return fileService.getFileContent(projectId, path).content();
    }

    @GetMapping("/projects/{projectId}/permissions/check")
    public boolean checkProjectPermission(@PathVariable Long projectId, @RequestParam ProjectPermission permission) {
        return projectService.hasPermission(projectId, permission);
    }
}

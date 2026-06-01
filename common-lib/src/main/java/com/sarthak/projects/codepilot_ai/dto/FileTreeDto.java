package com.sarthak.projects.codepilot_ai.dto;

import com.sarthak.projects.codepilot_ai.dto.project.FileNode;

import java.util.List;

public record FileTreeDto(
        List<FileNode> files
) {
}

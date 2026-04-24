package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.project.FileContentResponse;
import com.sarthak.projects.codepilot_ai.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}

package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.project.FileNode;
import com.sarthak.projects.codepilot_ai.entity.ProjectFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);
}

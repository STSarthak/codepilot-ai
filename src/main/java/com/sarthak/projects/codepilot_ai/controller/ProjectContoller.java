package com.sarthak.projects.codepilot_ai.controller;

import com.sarthak.projects.codepilot_ai.dto.project.ProjectRequest;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectResponse;
import com.sarthak.projects.codepilot_ai.dto.project.ProjectSummaryResponse;
import com.sarthak.projects.codepilot_ai.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectContoller {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProject(){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProject(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getProjectById(id,userId));
    }

    @PutMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request,userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectRequest request){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L;
        projectService.softDelete(id, userId);
        return ResponseEntity.noContent().build();
    }
}

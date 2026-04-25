package com.sarthak.projects.codepilot_ai.repository;

import com.sarthak.projects.codepilot_ai.entity.ProjectMember;
import com.sarthak.projects.codepilot_ai.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    boolean existsById(ProjectMemberId projectMemberId);

}

package com.sarthak.projects.codepilot_ai.repository;

import com.sarthak.projects.codepilot_ai.entity.ProjectMember;
import com.sarthak.projects.codepilot_ai.entity.ProjectMemberId;
import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    boolean existsById(ProjectMemberId projectMemberId);

    @Query("""
                SELECT pm.projectRole FROM ProjectMember pm
                where pm.id.projectId = :id AND pm.id.userId = :userId
            """)
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("id") Long projectId, @Param("userId") Long userId);
}

package com.sarthak.projects.codepilot_ai.repository;

import com.sarthak.projects.codepilot_ai.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
            select p from Project p 
            where p.deletedAt is null
            and exists (
                select 1 from ProjectMember pm
                where pm.id.userId =:userId
                and pm.id.projectId = p.id
            )
            order by p.updatedAt desc
            """)
    List<Project> findAllAccessibleProjectsByUser(@Param("userId") Long userId);

    @Query("""
            SELECT p FROM Project p
            WHERE p.id = :id 
                AND p.deletedAt IS NULL
                AND EXISTS(
                    SELECT 1 FROM ProjectMember pm
                    WHERE pm.id.userId = :userId
                    AND pm.id.projectId = :id
                )
            """)
    Optional<Project> findUserProjectById(@Param("userId") Long userId, @Param("id") Long id);
}

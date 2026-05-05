package com.sarthak.projects.codepilot_ai.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.sarthak.projects.codepilot_ai.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByStripePriceId(String id);
}

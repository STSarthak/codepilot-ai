package com.sarthak.projects.codepilot_ai.repository;

import com.sarthak.projects.codepilot_ai.entity.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    Optional<UsageLog> findByUserIdAndDate(Long userId, LocalDate date);
}

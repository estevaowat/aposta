package com.ewcode.friendsbigball.domain.match.repository;

import com.ewcode.friendsbigball.domain.common.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MatchRepository extends JpaRepository<Match, LocalDateTime> {
}

package com.ewcode.friendsbigball.domain.common.entity;

import com.ewcode.friendsbigball.domain.common.entity.enums.MatchStatusEnum;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
public record Match(LocalDateTime start,
                    MatchStatusEnum status,
                    Optional<Team> winnerTeam,
                    String score,
                    Player bestPlayer
) {
}

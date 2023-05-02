package com.ewcode.friendsbigball.app.bet;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BetRequestModel(int costumerId,
                              LocalDateTime matchId,
                              Integer winnerTeamId,
                              String score) {
}

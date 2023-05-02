package com.ewcode.friendsbigball.domain.bet;

import com.ewcode.friendsbigball.domain.common.entity.Costumer;
import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.common.entity.Team;
import com.ewcode.friendsbigball.domain.common.entity.enums.BetStatus;
import lombok.Builder;

import java.util.Optional;

@Builder
public record Bet(Costumer costumer,
                  Match match,
                  Optional<Team> winnerTeam,
                  String score,
                  BetStatus status
) {
    private static final int HIT_WINNER_TEAM = 5;
    private static final int HIT_QUANTITY_GOALS = 3;
    private static final int HIT_EXACT_SCORE_POINTS_AND_WINNER_TEAM = 10;

    private int getTotalOfGoals(String result) {
        if (result == null) {
            return 0;
        }

        String[] score = result.split("x");
        int score1 = Integer.parseInt(score[0]);
        int score2 = Integer.parseInt(score[1]);
        return score1 + score2;
    }
    public int getPoints() {
        int points = 0;

        boolean hitExactScore = match.score().equals(score);
        boolean hitWinnerTeam = match.winnerTeam().equals(winnerTeam);

        if (hitExactScore && hitWinnerTeam) {
            points += HIT_EXACT_SCORE_POINTS_AND_WINNER_TEAM;
        }

        if (hitWinnerTeam) {
            points += HIT_WINNER_TEAM;
        }

        int matchGoals = getTotalOfGoals(match.score());
        int betGoals = getTotalOfGoals(score);
        boolean hitQuantityGoals = matchGoals == betGoals;

        if (hitQuantityGoals) {
            points += HIT_QUANTITY_GOALS;
        }

        //TODO: Add points when hits match best player match

        return points;
    }


}

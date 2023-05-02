package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.domain.bet.Bet;
import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.common.entity.Player;
import com.ewcode.friendsbigball.domain.common.entity.Team;
import com.ewcode.friendsbigball.domain.common.entity.enums.BetStatus;
import com.ewcode.friendsbigball.domain.common.entity.enums.MatchStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

class CalculatePointsTest {
    /*
     * User hits exact score, winner team and quantityGoals
     * */
    @Test
    @DisplayName("Calculate points when bet hits exact score")
    void betCalculatePointsWhenHitsExactScore() {
        String score = "2x0";
        Team winnerTeam = createTeam();
        Player bestPlayer = createPlayer();
        Match match = createMatch(score, Optional.ofNullable(winnerTeam), bestPlayer);
        Bet bet = createBet(score, Optional.ofNullable(winnerTeam), match);

        final int hitMaxPoints = 18;
        Assertions.assertEquals(hitMaxPoints, bet.getPoints());
    }

    /*
     * User hits exact score, winner team and quantity of Goals
     * */
    @Test
    @DisplayName("Calculate points when bet hits a draw but not exact score")
    void calculatePointsWhenBetHitsADraw() {
        String betScore = "1x1";
        String matchScore = "2x2";
        Player bestPlayer = createPlayer();
        Optional<Team> winner = Optional.empty();
        Match match = createMatch(matchScore, winner, bestPlayer);
        Bet bet = createBet(betScore, winner, match);

        final int hitMaxPoints = 5;
        Assertions.assertEquals(hitMaxPoints, bet.getPoints());
    }

    @Test
    @DisplayName("Calculate points when bet goes wrong")
    void calculatePointsWhenBetGoesWrong() {
        String betScore = "1x1";
        Player bestPlayer = createPlayer();
        Team winner = createTeam();
        Match match = createMatch("2x1", Optional.ofNullable(winner), bestPlayer);
        Bet bet = createBet(betScore, Optional.empty(), match);

        final int hitMaxPoints = 0;
        Assertions.assertEquals(hitMaxPoints, bet.getPoints());
    }


    private Match createMatch(String score, Optional<Team> winner, Player bestPlayer) {
        return Match.builder()
                .start(LocalDateTime.now())
                .status(MatchStatusEnum.FINISHED)
                .score(score)
                .winnerTeam(winner)
                .bestPlayer(bestPlayer)
                .build();
    }

    private Bet createBet(String score, Optional<Team> winnerTeam, Match match) {
        return Bet.builder()
                .match(match)
                .winnerTeam(winnerTeam)
                .score(score)
                .betStatus(BetStatus.PENDING)
                .build();
    }
    private Team createTeam() {
        return Team.builder().name("Brazil").build();
    }

    private Player createPlayer() {
        return Player.builder().name("Ronaldo").build();
    }
}

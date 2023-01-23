package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.common.entity.*;
import com.ewcode.friendsbigball.common.entity.enums.GameStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CalculatePointsTest {

  @Test
  @DisplayName("Calculate points")
  /*
   * User hits exact score, winner team and quantityGoals
   * */
  void betCalculatePoints() {

    BetPrimaryKey betPrimaryKey = new BetPrimaryKey();
    betPrimaryKey.setGame(createGame());

    Bet bet = new Bet();
    bet.setPrimaryKey(betPrimaryKey);
    bet.setWinnerTeam(createTeam());
    bet.setResult("2x0");

    bet.calculatePoints();
    final int hitMaxPoints = 18;
    Assertions.assertEquals(hitMaxPoints, bet.getPoints());
  }

  private Game createGame() {
    Team winner = createTeam();
    Player bestPlayer = createPlayer();

    Game game = new Game();
    game.setStart(LocalDateTime.now());
    game.setStatus(GameStatusEnum.FINISHED);
    game.setScore("2x0");
    game.setWinnerTeam(winner);
    game.setBestPlayer(bestPlayer);

    return game;
  }

  private Team createTeam() {
    Team team = new Team();
    team.setId(1);
    team.setName("Brazil");
    return team;
  }

  private Player createPlayer() {
    Player player = new Player();
    player.setId(1);
    player.setName("Ronaldo");
    return player;
  }
}

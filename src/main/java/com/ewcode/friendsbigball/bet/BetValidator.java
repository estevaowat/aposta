package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.costumer.service.CostumerService;
import com.ewcode.friendsbigball.game.service.GameService;
import com.ewcode.friendsbigball.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetValidator {

  private final CostumerService costumerService;
  private final GameService gameService;
  private final TeamService teamService;

  @Autowired
  public BetValidator(
          CostumerService costumerService, GameService gameService, TeamService teamService) {
    this.costumerService = costumerService;
    this.gameService = gameService;
    this.teamService = teamService;
  }
}

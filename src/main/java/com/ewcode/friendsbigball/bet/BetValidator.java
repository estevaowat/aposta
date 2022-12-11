package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.game.service.GameService;
import com.ewcode.friendsbigball.team.TeamService;
import com.ewcode.friendsbigball.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetValidator {

    private final UserService userService;
    private final GameService gameService;
    private final TeamService teamService;

    @Autowired
    public BetValidator(UserService userService, GameService gameService, TeamService teamService) {
        this.userService = userService;
        this.gameService = gameService;
        this.teamService = teamService;
    }
}

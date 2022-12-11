package com.ewcode.friendsbigball.game;

import com.ewcode.friendsbigball.game.dto.GameDto;
import com.ewcode.friendsbigball.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/game")
@RestController
public class GameConsumer {

    private final GameService gameService;

    @Autowired
    public GameConsumer(GameService gameService) {
        this.gameService = gameService;
    }

    public void saveGame(GameDto game) throws Exception {
        //validate data to save in database
        gameService.save(game);

        //save the game
        //compute the points in bets
    }

}

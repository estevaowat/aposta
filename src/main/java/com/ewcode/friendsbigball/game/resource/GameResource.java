package com.ewcode.friendsbigball.game.resource;

import com.ewcode.friendsbigball.common.entity.Game;
import com.ewcode.friendsbigball.game.dto.GameDto;
import com.ewcode.friendsbigball.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/game")
@RestController
public class GameResource {

  private final GameService gameService;

  @Autowired
  public GameResource(GameService gameService) {
    this.gameService = gameService;
  }

  public ResponseEntity<Game> saveGame(GameDto gameDto) {
    Game game = gameService.save(gameDto);
    return ResponseEntity.ok(game);
  }
}

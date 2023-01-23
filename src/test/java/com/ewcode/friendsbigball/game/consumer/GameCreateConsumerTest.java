package com.ewcode.friendsbigball.game.consumer;

import com.ewcode.friendsbigball.common.entity.Game;
import com.ewcode.friendsbigball.common.entity.enums.GameStatusEnum;
import com.ewcode.friendsbigball.game.dto.GameDto;
import com.ewcode.friendsbigball.game.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GameCreateConsumerTest {

  @Mock private GameService gameService;

  private ObjectMapper mapper;

  private GameCreateConsumer gameCreateConsumer;

  private GameDto createNewGameDto() {
    return new GameDto(LocalDateTime.now(), GameStatusEnum.NOT_STARTED, null, "2x0", null);
  }

  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper();
    gameCreateConsumer = new GameCreateConsumer(mapper, gameService);
  }

  @Test
  void consumeValidMessage() throws JsonProcessingException {
    List<GameDto> games = new ArrayList<>();
    List<Game> gamesMock = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      GameDto gameDto = createNewGameDto();
      Game game = createGameEntity(gameDto);
      games.add(gameDto);
      gamesMock.add(game);
    }

    String message = mapper.writeValueAsString(games);

    Mockito.when(gameService.saveAll(Mockito.anyList())).thenReturn(gamesMock);
    List<Game> savedGames = gameCreateConsumer.consume(message);
    Assertions.assertEquals(10, savedGames.size());
  }

  private Game createGameEntity(GameDto gameDto) {
    Game game = new Game();
    game.setStart(gameDto.start());
    game.setStatus(gameDto.status());
    game.setScore(gameDto.formatScore());
    game.setWinnerTeam(null);
    game.setBestPlayer(null);
    return game;
  }
}

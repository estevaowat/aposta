package com.ewcode.friendsbigball.game.consumer;

import com.ewcode.friendsbigball.common.entity.Game;
import com.ewcode.friendsbigball.game.dto.GameDto;
import com.ewcode.friendsbigball.game.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GameCreateConsumer {
  private final ObjectMapper mapper;
  private final GameService gameService;
  Logger logger = LogManager.getLogger(GameCreateConsumer.class);

  public GameCreateConsumer(ObjectMapper mapper, GameService gameService) {
    this.mapper = mapper;
    this.gameService = gameService;
  }

  public List<Game> consume(String message) throws JsonProcessingException {
    logger.info("Starting consume save game topic");
    logger.info("Deserializing message");

    List<GameDto> games = mapper.readValue(message, new TypeReference<>() {});

    logger.info("Saving games");
    List<Game> savedGames = gameService.saveAll(games);

    String savedGamesMessage = "saved " + savedGames.size() + " games";
    logger.info(savedGamesMessage);

    return savedGames;
  }
}

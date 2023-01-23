package com.ewcode.friendsbigball.game.service;

import com.ewcode.friendsbigball.common.entity.Game;
import com.ewcode.friendsbigball.common.entity.Player;
import com.ewcode.friendsbigball.common.entity.Team;
import com.ewcode.friendsbigball.game.dto.GameDto;
import com.ewcode.friendsbigball.game.repository.GameRepository;
import com.ewcode.friendsbigball.player.repository.PlayerRepository;
import com.ewcode.friendsbigball.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

  private final TeamRepository teamRepository;
  private final PlayerRepository playerRepository;
  private final GameRepository gameRepository;

  public GameService(
          TeamRepository teamRepository,
          PlayerRepository playerRepository,
          GameRepository gameRepository) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
    this.gameRepository = gameRepository;
  }

  public Game save(GameDto gameDto) {
    Game game = createGame(gameDto);
    return gameRepository.save(game);
  }

  private Game createGame(GameDto gameDto) {
    Game game = gameRepository.findById(gameDto.start()).orElse(new Game());
    return populateGame(game, gameDto);
  }

  public List<Game> saveAll(List<GameDto> games) {
    List<Game> gamesToSave = games.stream().map(this::createGame).toList();
    return gameRepository.saveAll(gamesToSave);
  }

  private Game populateGame(Game game, GameDto gameDto) {
    Team teamWinner = teamRepository.findById(gameDto.winnerTeamId()).orElse(null);
    Player bestPlayer = playerRepository.findById(gameDto.bestPlayerId()).orElse(null);

    game.setStart(gameDto.start());
    game.setStatus(gameDto.status());
    game.setScore(gameDto.formatScore());
    game.setWinnerTeam(teamWinner);
    game.setBestPlayer(bestPlayer);
    return game;
  }
}

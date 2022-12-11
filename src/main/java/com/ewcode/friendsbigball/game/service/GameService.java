package com.ewcode.friendsbigball.game.service;

import com.ewcode.friendsbigball.common.entities.Game;
import com.ewcode.friendsbigball.common.entities.Player;
import com.ewcode.friendsbigball.common.entities.Team;
import com.ewcode.friendsbigball.common.entities.enums.GameStatusEnum;
import com.ewcode.friendsbigball.game.dto.GameDto;
import com.ewcode.friendsbigball.game.repository.GameRepository;
import com.ewcode.friendsbigball.player.repository.PlayerRepository;
import com.ewcode.friendsbigball.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;


    public GameService(TeamRepository teamRepository,
                       PlayerRepository playerRepository,
                       GameRepository gameRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public Game save(GameDto game) throws Exception {
        Game gameToSave = new Game();
        gameToSave.setStart(game.start());
        gameToSave.setStatus(GameStatusEnum.FINISHED);

        String formattedScore = game.formatScore();
        gameToSave.setScore(formattedScore);

        Optional<Team> teamOpt = teamRepository.findById(game.winnerTeamId());

        if(teamOpt.isEmpty()) {
            throw new Exception();
        }

        Team winnerTeam = teamOpt.get();

        gameToSave.setWinnerTeam(winnerTeam);

        Optional<Player> bestPlayerOpt = playerRepository.findById(game.bestPlayerId());

        if(bestPlayerOpt.isEmpty()) {
            throw new Exception();
        }

        Player bestPlayer = bestPlayerOpt.get();

        gameToSave.setBestPlayer(bestPlayer);

        //        Game gameSaved = gameRepository.save(gameToSave);
        //      return gameSaved;
        return new Game();
    }

    public Optional<Game> findById(int gameId) {
        return gameRepository.findById(gameId);
    }


}

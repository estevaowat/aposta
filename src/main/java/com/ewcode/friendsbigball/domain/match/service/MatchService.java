package com.ewcode.friendsbigball.domain.match.service;

import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.common.entity.Player;
import com.ewcode.friendsbigball.domain.common.entity.Team;
import com.ewcode.friendsbigball.domain.match.dto.MatchRequestDTO;
import com.ewcode.friendsbigball.domain.match.repository.MatchRepository;
import com.ewcode.friendsbigball.domain.repository.PlayerRepository;
import com.ewcode.friendsbigball.domain.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    public MatchService(
            TeamRepository teamRepository,
            PlayerRepository playerRepository,
            MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public Match save(MatchRequestDTO matchRequestDTO) {
        Match match = createMatch(matchRequestDTO);
        return matchRepository.save(match);
    }

    private Match createMatch(MatchRequestDTO matchRequestDTO) {
        Optional<Match> matchOpt = matchRepository.findById(matchRequestDTO.start());
        return matchOpt.map(match -> populateMatch(matchRequestDTO)).orElse(Match.builder().build());

    }

    public List<Match> saveAll(List<MatchRequestDTO> matchs) {
        List<Match> matchsToSave = matchs.stream().map(this::createMatch).toList();
        return matchRepository.saveAll(matchsToSave);
    }

    private Match populateMatch(MatchRequestDTO matchRequestDTO) {
        Optional<Team> winnerTeam = teamRepository.findById(matchRequestDTO.winnerTeamId());

        Player bestPlayer = playerRepository
                .findById(matchRequestDTO.bestPlayerId())
                .orElse(null);

        return Match.builder()
                .start(matchRequestDTO.start())
                .status(matchRequestDTO.status())
                .score(matchRequestDTO.score())
                .winnerTeam(winnerTeam)
                .bestPlayer(bestPlayer)
                .build();
    }
}

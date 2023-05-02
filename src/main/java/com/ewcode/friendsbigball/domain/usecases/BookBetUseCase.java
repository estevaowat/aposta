package com.ewcode.friendsbigball.domain.usecases;

import com.ewcode.friendsbigball.app.bet.BetRequestModel;
import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import com.ewcode.friendsbigball.domain.bet.Bet;
import com.ewcode.friendsbigball.domain.bet.repository.BetRepository;
import com.ewcode.friendsbigball.domain.common.entity.Costumer;
import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.common.entity.Team;
import com.ewcode.friendsbigball.domain.common.entity.enums.BetStatus;
import com.ewcode.friendsbigball.domain.costumer.repository.CostumerRepository;
import com.ewcode.friendsbigball.domain.match.repository.MatchRepository;
import com.ewcode.friendsbigball.domain.team.repository.TeamRepository;

import java.util.Optional;

public class BookBetUseCase {
    private final Logger logger;
    private final BetRepository betRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final CostumerRepository costumerRepository;
    public BookBetUseCase(Logger logger,
                          BetRepository betRepository,
                          TeamRepository teamRepository,
                          MatchRepository matchRepository,
                          CostumerRepository costumerRepository) {
        this.logger = logger;
        this.betRepository = betRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.costumerRepository = costumerRepository;
    }

    public void execute(BetRequestModel betRequest) {
        LogBuilder logBuilder = new LogBuilder().className(BookBetUseCase.class.getName());

        Optional<Costumer> costumerOpt = costumerRepository.findById(betRequest.costumerId());

        if (costumerOpt.isEmpty()) {
            logger.error(logBuilder.message("Costumer not found."));
            return;
        }

        Optional<Match> matchOpt = matchRepository.findById(betRequest.matchId());

        if (matchOpt.isEmpty()) {
            logger.error(logBuilder.message("Match not found."));
            return;
        }

        Optional<Team> winnerTeam = teamRepository.findById(betRequest.winnerTeamId());

        Bet bet = Bet.builder()
                .costumer(costumerOpt.get())
                .match(matchOpt.get())
                .winnerTeam(winnerTeam)
                .score(betRequest.score())
                .status(BetStatus.PENDING).build();

        betRepository.save(bet);
    }
}

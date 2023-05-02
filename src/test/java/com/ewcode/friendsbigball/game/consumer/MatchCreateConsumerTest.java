package com.ewcode.friendsbigball.game.consumer;

import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.common.entity.Player;
import com.ewcode.friendsbigball.domain.common.entity.Team;
import com.ewcode.friendsbigball.domain.common.entity.enums.MatchStatusEnum;
import com.ewcode.friendsbigball.domain.match.consumer.MatchCreateConsumer;
import com.ewcode.friendsbigball.domain.match.dto.MatchRequestDTO;
import com.ewcode.friendsbigball.domain.match.repository.MatchRepository;
import com.ewcode.friendsbigball.domain.match.service.MatchService;
import com.ewcode.friendsbigball.domain.repository.PlayerRepository;
import com.ewcode.friendsbigball.domain.team.repository.TeamRepository;
import com.ewcode.friendsbigball.infra.log.Logger4JImpl;
import com.ewcode.friendsbigball.infra.mapper.JacksonObjectMapper;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MatchCreateConsumerTest {
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private MatchRepository matchRepository;

    private ObjectMapper objectMapper;

    private com.ewcode.friendsbigball.domain.match.service.MatchService matchService;

    private MatchCreateConsumer matchCreateConsumer;

    private MatchRequestDTO createNewMatchRequestDTO() {
        return MatchRequestDTO.builder()
                .start(LocalDateTime.now())
                .status(MatchStatusEnum.NOT_STARTED)
                .score("2x0")
                .winnerTeamId(1)
                .bestPlayerId(1)
                .build();
    }

    @BeforeEach
    void setUp() {
        Logger4JImpl logger = new Logger4JImpl();
        JacksonObjectMapper jacksonObjectMapper = new JacksonObjectMapper();
        objectMapper = new ObjectMapper();
        matchService = new MatchService(teamRepository, playerRepository, matchRepository);
        matchCreateConsumer = new MatchCreateConsumer(jacksonObjectMapper, logger, matchService);
    }

    @Test
    void consumeValidMessage() throws JsonProcessingException {
        MatchRequestDTO matchRequestDTO = createNewMatchRequestDTO();
        String message = objectMapper.writeValueAsString(matchRequestDTO);
        Mockito.when(matchRepository.save(Mockito.any(Match.class))).thenReturn(matchMock());

        Match savedMatch = matchCreateConsumer.consume(message);

        Assertions.assertEquals(savedMatch.bestPlayer().name(), "Neymar");
        Assertions.assertEquals(savedMatch.score(), "2x0");
    }

    private Match createGameEntity(MatchRequestDTO matchRequestDTO) {
        return Match.builder()
                .start(matchRequestDTO.start())
                .status(matchRequestDTO.status())
                .score(matchRequestDTO.score())
                .build();
    }

    private Match matchMock() {
        Team team = Team.builder().name("Brazil").build();
        Player player = Player.builder().name("Neymar").build();

        return Match.builder()
                .start(LocalDateTime.now())
                .status(MatchStatusEnum.NOT_STARTED)
                .score("2x0")
                .winnerTeam(Optional.ofNullable(team))
                .bestPlayer(player)
                .build();


    }
}

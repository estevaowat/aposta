package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.bet.service.BetService;
import com.ewcode.friendsbigball.common.entity.*;
import com.ewcode.friendsbigball.common.entity.enums.BetStatus;
import com.ewcode.friendsbigball.common.entity.enums.GameStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BetProducerTest {
  private BetProducer betProducer;
  @Mock private BetService betService;
  @Mock private KafkaTemplate<Integer, String> kafkaTemplate;

  @BeforeEach
  void setUp() {
    ObjectMapper mapper = new ObjectMapper();
    betProducer = new BetProducer(kafkaTemplate, betService, mapper);
  }

  @Test
  @DisplayName("should send pending bet to broker")
  void shouldSendPendingBetsToBroker() throws JsonProcessingException {
    List<Bet> betsToBook = createBetsToBook();
    Mockito.when(betService.findBetsToBook()).thenReturn(betsToBook);
    int sentBets = betProducer.produce();
    Assertions.assertEquals(betsToBook.size(), sentBets);
  }

  private List<Bet> createBetsToBook() {
    List<Bet> betsToBook = new ArrayList<>();

    Bet bet = createPendingBet();

    for (int i = 0; i < 1000; i++) {
      betsToBook.add(bet);
    }

    return betsToBook;
  }

  private Bet createPendingBet() {
    Costumer costumer = createCostumer();
    Game game1 = createGame();

    BetPrimaryKey betPrimaryKey = new BetPrimaryKey();
    betPrimaryKey.setCostumer(costumer);
    betPrimaryKey.setGame(game1);

    Bet bet = new Bet();
    bet.setPrimaryKey(betPrimaryKey);
    bet.setResult("2x0");
    bet.setStatus(BetStatus.PENDING);
    return bet;
  }

  private Costumer createCostumer() {
    Costumer costumer = new Costumer();
    costumer.setId(1);
    costumer.setName("teste");
    costumer.setEmail("teste@teste.com");
    return costumer;
  }

  private Game createGame() {
    Team winner = createTeam();
    Player bestPlayer = createPlayer();

    Game game = new Game();
    game.setStart(LocalDateTime.now());
    game.setStatus(GameStatusEnum.FINISHED);
    game.setScore("2x0");
    game.setWinnerTeam(winner);
    game.setBestPlayer(bestPlayer);

    return game;
  }

  private Team createTeam() {
    Team team = new Team();
    team.setId(1);
    team.setName("Brazil");
    return team;
  }

  private Player createPlayer() {
    Player player = new Player();
    player.setId(1);
    player.setName("Ronaldo");
    return player;
  }
}

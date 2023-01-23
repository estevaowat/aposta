package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.bet.service.BetService;
import com.ewcode.friendsbigball.common.entity.Bet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BetProducer {
  private final KafkaTemplate<Integer, String> kafkaTemplate;
  private final BetService betService;
  private final ObjectMapper mapper;

  Logger logger = LogManager.getLogger(BetProducer.class);

  public BetProducer(
          KafkaTemplate<Integer, String> kafkaTemplate, BetService betService, ObjectMapper mapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.betService = betService;
    this.mapper = mapper;
  }

  public int produce() throws JsonProcessingException {
    logger.info("Finding bets to book");
    List<Bet> bets = betService.findBetsToBook();

    AtomicInteger count = new AtomicInteger();

    for (Bet bet : bets) {
      String message = mapper.writeValueAsString(bet);
      logger.info(message);
      kafkaTemplate.send("SENT_BET", LocalDateTime.now().getNano(), message);
      count.incrementAndGet();
    }

    String totalBetsSentLog = "Sent bets: " + count.get() + " of " + bets.size();
    logger.info(totalBetsSentLog);
    return count.get();
  }
}

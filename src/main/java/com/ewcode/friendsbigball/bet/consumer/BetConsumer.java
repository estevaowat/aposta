package com.ewcode.friendsbigball.bet.consumer;

import com.ewcode.friendsbigball.bet.service.BetService;
import com.ewcode.friendsbigball.common.entity.Bet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BetConsumer {
  private final ObjectMapper mapper;
  private final BetService betService;
  Logger logger = LogManager.getLogger(BetConsumer.class);

  public BetConsumer(ObjectMapper mapper, BetService betService) {
    this.mapper = mapper;
    this.betService = betService;
  }

  @KafkaListener(topics = "BOOK_BETS", groupId = "foo")
  public void listen(String message) throws JsonProcessingException {
      logger.info("Consuming BOOK_BETS messsage");
      logger.debug("message = {}", message);
      logger.info("Deserializing message");
    List<Bet> bets = mapper.readValue(message, new TypeReference<>() {});
      betService.book(bets);
  }
}

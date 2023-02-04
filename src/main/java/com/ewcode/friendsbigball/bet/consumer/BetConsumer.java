package com.ewcode.friendsbigball.bet.consumer;

import com.ewcode.friendsbigball.bet.dto.BookBetDto;
import com.ewcode.friendsbigball.bet.service.BetService;
import com.ewcode.friendsbigball.config.log.LogInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BetConsumer {
  private final ObjectMapper mapper;
  private final BetService betService;
  private final LogInfo logInfo;
  Logger logger = LogManager.getLogger(BetConsumer.class);

  public BetConsumer(ObjectMapper mapper, BetService betService, LogInfo logInfo) {
    this.mapper = mapper;
    this.betService = betService;
    this.logInfo = logInfo;
  }

  @KafkaListener(topics = "book_bet", id = "book_bet")
  public void listen(byte[] message) throws IOException {
    logger.info(LogInfo.template, logInfo.getCorrelationId(), "converting byte[] to dto");

    BookBetDto bet = mapper.readValue(message, BookBetDto.class);
    logger.info(LogInfo.template, logInfo.getCorrelationId(), "converted byte[] to dto");

    logger.info(LogInfo.template, logInfo.getCorrelationId(), "starting book bet");

    betService.book(bet);
  }
}

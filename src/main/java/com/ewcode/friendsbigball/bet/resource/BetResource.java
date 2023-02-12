package com.ewcode.friendsbigball.bet.resource;

import com.ewcode.friendsbigball.bet.dto.BookBetDto;
import com.ewcode.friendsbigball.config.log.LogInfo;
import com.ewcode.friendsbigball.config.messaging.kafka.topics.BetTopic;
import com.ewcode.friendsbigball.messaging.sender.KafkaMessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("bet")
public class BetResource {

    private final KafkaMessageSender kafkaMessageSender;
    private final ObjectMapper mapper;
    private final LogInfo logInfo;
    private final Logger logger = LogManager.getLogger(BetResource.class);

    @Autowired
    public BetResource(KafkaMessageSender kafkaMessageSender, ObjectMapper mapper, LogInfo logInfo) {
        this.kafkaMessageSender = kafkaMessageSender;
        this.mapper = mapper;
        this.logInfo = logInfo;
    }

    @PostMapping
    public ResponseEntity<Void> sendBetsToBook() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("create")
    public ResponseEntity<String> createBet(@RequestBody BookBetDto body) throws JsonProcessingException {
        logger.info(LogInfo.template, logInfo.getCorrelationId(), "Starting request");

        byte[] dto = mapper.writeValueAsString(body).getBytes(StandardCharsets.UTF_8);
        logger.info(LogInfo.template, logInfo.getCorrelationId(), "Converting dto to byte[]");
        logger.info(LogInfo.template, logInfo.getCorrelationId(), "Sending dto to topic " + BetTopic.BOOK_BET_TOPIC);
        kafkaMessageSender.send(BetTopic.BOOK_BET_TOPIC, dto);
        logger.info(LogInfo.template, logInfo.getCorrelationId(), "Message sent");

        return ResponseEntity.ok("Message sent");
    }
}

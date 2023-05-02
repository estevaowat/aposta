package com.ewcode.friendsbigball.domain.bet.resource;

import com.ewcode.friendsbigball.app.bet.BetRequestModel;
import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import com.ewcode.friendsbigball.app.mapper.Mapper;
import com.ewcode.friendsbigball.domain.messaging.sender.KafkaMessageSender;
import com.ewcode.friendsbigball.infra.kafka.KafkaTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bet")
public class BetResource {

    private final KafkaMessageSender kafkaMessageSender;
    private final Mapper mapper;
    private final Logger logger;

    @Autowired
    public BetResource(KafkaMessageSender kafkaMessageSender,
                       Mapper mapper,
                       Logger logger) {
        this.kafkaMessageSender = kafkaMessageSender;
        this.mapper = mapper;
        this.logger = logger;
    }

    @PostMapping
    public ResponseEntity<Void> sendBetsToBook() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("create")
    public ResponseEntity<String> createBet(@RequestBody BetRequestModel body) throws JsonProcessingException {
        LogBuilder logBuilder = new LogBuilder().className(BetResource.class.getName());
        logger.info(logBuilder.message("Starting request"));

        logger.info(logBuilder.message("Converting dto to byte[]"));
        byte[] dto = mapper.toByte(body);
        logger.info(logBuilder.message("Convert body to byte[]"));

        logger.info(logBuilder.message("Sending dto to topic " + KafkaTopic.BOOK_BET_TOPIC));
        kafkaMessageSender.send(KafkaTopic.BOOK_BET_TOPIC, dto);
        logger.info(logBuilder.message("Message sent"));

        return ResponseEntity.ok("Message sent");
    }
}

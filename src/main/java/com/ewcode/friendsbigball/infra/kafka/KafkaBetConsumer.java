package com.ewcode.friendsbigball.infra.kafka;

import com.ewcode.friendsbigball.app.bet.BetRequestModel;
import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import com.ewcode.friendsbigball.app.mapper.Mapper;
import com.ewcode.friendsbigball.domain.usecases.BookBetUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaBetConsumer {
    private final Logger logger;
    private final Mapper mapper;
    private final BookBetUseCase bookBetUseCase;
    public KafkaBetConsumer(Logger logger, Mapper mapper, BookBetUseCase bookBetUseCase) {
        this.logger = logger;
        this.mapper = mapper;
        this.bookBetUseCase = bookBetUseCase;
    }

    @KafkaListener(topics = "book_bet", id = "book_bet")
    public void listen(byte[] message) throws IOException {
        LogBuilder logInfo = new LogBuilder().className(KafkaBetConsumer.class.getName());
        logger.info(logInfo.message("converting byte[] to dto"));

        BetRequestModel bet = mapper.convert(message, BetRequestModel.class);
        logger.info(logInfo.message("converted byte[] to dto"));

        logger.info(logInfo.message("starting book bet"));
        bookBetUseCase.execute(bet);
        logger.info(logInfo.message("bet booked"));
    }
}

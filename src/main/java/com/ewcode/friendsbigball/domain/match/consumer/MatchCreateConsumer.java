package com.ewcode.friendsbigball.domain.match.consumer;

import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import com.ewcode.friendsbigball.app.mapper.Mapper;
import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.match.dto.MatchRequestDTO;
import com.ewcode.friendsbigball.domain.match.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MatchCreateConsumer {
    private final Mapper mapper;
    private final MatchService matchService;
    private final Logger logger;

    public MatchCreateConsumer(Mapper mapper, Logger logger, MatchService matchService) {
        this.mapper = mapper;
        this.logger = logger;
        this.matchService = matchService;

    }

    public Match consume(String message) throws JsonProcessingException {
        LogBuilder logBuilder = new LogBuilder().className(MatchCreateConsumer.class.getName());
        logger.info(logBuilder.message("Starting consume save game topic"));

        logger.info(logBuilder.message("Deserializing message"));
        MatchRequestDTO matches = mapper.convert(message, MatchRequestDTO.class);
        logger.info(logBuilder.message("message deserialized"));

        logger.info(logBuilder.message("Saving match"));
        Match savedMatch = matchService.save(matches);
        logger.info(logBuilder.message("Match created sucessfully"));

        return savedMatch;
    }
}

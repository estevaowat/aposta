package com.ewcode.friendsbigball.game.dto;

import com.ewcode.friendsbigball.common.entity.enums.GameStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public record GameDto(
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime start,
        GameStatusEnum status,
        Integer winnerTeamId,
        String score,
        Integer bestPlayerId
) {
    public String formatScore() {
        final String regexPattern = "\\d-\\d";
        boolean matches = Pattern.matches(regexPattern, score);

        if(!matches) {
            return null;
        }

        String[] scores = score.split("-");
        int scoreLeft = Integer.parseInt(scores[0]);
        int scoreRight = Integer.parseInt(scores[1]);

        if(scoreRight > scoreLeft) {
            int scoreTemp = scoreLeft;
            scoreLeft = scoreRight;
            scoreRight = scoreTemp;
        }

        return scoreLeft + "-" + scoreRight;
    }
}

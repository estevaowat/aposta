package com.ewcode.friendsbigball.domain.match.dto;

import com.ewcode.friendsbigball.domain.common.entity.enums.MatchStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Builder
public record MatchRequestDTO(
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime start,
        MatchStatusEnum status,
        Integer winnerTeamId,
        String score,
        Integer bestPlayerId
) {
    public String formatScore() {
        final String regexPattern = "\\d-\\d";
        boolean matches = Pattern.matches(regexPattern, score);

        if (!matches) {
            return null;
        }

        String[] scores = score.split("-");
        int scoreLeft = Integer.parseInt(scores[0]);
        int scoreRight = Integer.parseInt(scores[1]);

        if (scoreRight > scoreLeft) {
            int scoreTemp = scoreLeft;
            scoreLeft = scoreRight;
            scoreRight = scoreTemp;
        }

        return scoreLeft + "-" + scoreRight;
    }
}

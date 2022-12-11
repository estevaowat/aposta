package com.ewcode.friendsbigball.game.dto;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public record GameDto(LocalDateTime start,
                      Integer winnerTeamId,
                      String score,
                      int bestPlayerId
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

        String scoreFormatted = scoreLeft + "-" + scoreRight;
        return scoreFormatted;
    }


}

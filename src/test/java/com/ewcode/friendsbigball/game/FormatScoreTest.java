package com.ewcode.friendsbigball.game;

import com.ewcode.friendsbigball.domain.common.entity.enums.MatchStatusEnum;
import com.ewcode.friendsbigball.domain.match.dto.MatchRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

class FormatScoreTest {

    @DisplayName("when higher score is in left ")
    @ParameterizedTest
    @CsvSource({"2-1,2-1", "1-2,2-1", "1-1,1-1"})
    void shouldHigherScoreBeInLeft(String score, String expectedScore) {
        MatchRequestDTO game = createFakeGame(score);
        String formattedScore = game.formatScore();
        Assertions.assertEquals(expectedScore, formattedScore);
    }

    @DisplayName("when score is not in correct format")
    @ParameterizedTest
    @ValueSource(strings = {"", "alemanha-brasil", "1d-12d", "d1-12d"})
    void shouldReturnNullWhenScoreNotMatchesRegex(String score) {
        MatchRequestDTO game = createFakeGame(score);
        String formattedScore = game.formatScore();
        Assertions.assertNull(formattedScore);
    }

    private MatchRequestDTO createFakeGame(String score) {
        return new MatchRequestDTO(LocalDateTime.now(), MatchStatusEnum.FINISHED, 1, score, 10);
    }
}

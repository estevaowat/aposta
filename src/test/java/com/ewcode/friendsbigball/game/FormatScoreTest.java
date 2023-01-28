package com.ewcode.friendsbigball.game;

import com.ewcode.friendsbigball.common.entity.enums.GameStatusEnum;
import com.ewcode.friendsbigball.game.dto.GameDto;
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
    GameDto game = createFakeGame(score);
    String formattedScore = game.formatScore();
    Assertions.assertEquals(expectedScore, formattedScore);
  }

  @DisplayName("when score is not in correct format")
  @ParameterizedTest
  @ValueSource(strings = {"", "alemanha-brasil", "1d-12d", "d1-12d"})
  void shouldReturnNullWhenScoreNotMatchesRegex(String score) {
    GameDto game = createFakeGame(score);
    String formattedScore = game.formatScore();
    Assertions.assertNull(formattedScore);
  }

  private GameDto createFakeGame(String score) {
    return new GameDto(LocalDateTime.now(), GameStatusEnum.FINISHED, 1, score, 10);
  }
}

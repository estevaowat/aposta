package com.ewcode.friendsbigball.common.entity;

import com.ewcode.friendsbigball.common.entity.enums.GameStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "game")
public class Game implements Serializable {

  @Serial private static final long serialVersionUID = -7547870246856534133L;

  @Id
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime start;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private GameStatusEnum status;

  @ManyToOne
  @JoinColumn(name = "winner_team_id")
  private Team winnerTeam;

  @Column(name = "game_score")
  private String score;

  @ManyToOne
  @JoinColumn(name = "best_player_id")
  private Player bestPlayer;

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public GameStatusEnum getStatus() {
    return status;
  }

  public void setStatus(GameStatusEnum status) {
    this.status = status;
  }

  public Team getWinnerTeam() {
    return winnerTeam;
  }

  public void setWinnerTeam(Team winnerTeam) {
    this.winnerTeam = winnerTeam;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public Player getBestPlayer() {
    return bestPlayer;
  }

  public void setBestPlayer(Player bestPlayer) {
    this.bestPlayer = bestPlayer;
  }

  public Integer getGoals() {
    if (score == null) {
      return null;
    }

    String[] scoreSplit = score.split("x");
    int score1 = Integer.parseInt(scoreSplit[0]);
    int score2 = Integer.parseInt(scoreSplit[1]);
    return score1 + score2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Game game = (Game) o;

    if (!getStart().equals(game.getStart())) {
      return false;
    }
    if (getStatus() != game.getStatus()) {
      return false;
    }
    if (getWinnerTeam() != null
        ? !getWinnerTeam().equals(game.getWinnerTeam())
        : game.getWinnerTeam() != null) {
      return false;
    }
    if (getScore() != null ? !getScore().equals(game.getScore()) : game.getScore() != null) {
      return false;
    }
    return getBestPlayer() != null
        ? getBestPlayer().equals(game.getBestPlayer())
        : game.getBestPlayer() == null;
  }

  @Override
  public int hashCode() {
    int result = getStart().hashCode();
    result = 31 * result + getStatus().hashCode();
    result = 31 * result + (getWinnerTeam() != null ? getWinnerTeam().hashCode() : 0);
    result = 31 * result + (getScore() != null ? getScore().hashCode() : 0);
    result = 31 * result + (getBestPlayer() != null ? getBestPlayer().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Game{"
        + "start="
        + start
        + ", status="
        + status
        + ", winnerTeam="
        + winnerTeam
        + ", score='"
        + score
        + '\''
        + ", bestPlayer="
        + bestPlayer
        + '}';
  }
}

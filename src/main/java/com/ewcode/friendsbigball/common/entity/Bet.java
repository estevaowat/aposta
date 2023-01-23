package com.ewcode.friendsbigball.common.entity;

import com.ewcode.friendsbigball.common.entity.enums.BetStatus;
import jakarta.persistence.*;

@Entity(name = "bet")
public class Bet {
  private static final int HIT_EXACT_SCORE_POINTS_AND_WINNER_TEAM = 10;
  private static final int HIT_WINNER_TEAM = 5;
  private static final int HIT_QUANTITY_GOALS = 3;
  @EmbeddedId private BetPrimaryKey primaryKey;

  @ManyToOne
  @JoinColumn(name = "winner_team_id")
  private Team winnerTeam;

  @Column(name = "game_score")
  private String result;

  @Column(name = "points")
  private Integer points = 0;

  @Column(name = "status")
  private BetStatus status;

  public Bet() {
    //
  }

  public Integer getGoals() {
    String[] score = result.split("x");
    int score1 = Integer.parseInt(score[0]);
    int score2 = Integer.parseInt(score[1]);
    return score1 + score2;
  }

  public void calculatePoints() {
    boolean hitExactScore = primaryKey.getGame().getScore().equals(result);
    boolean hitWinnerTeam = primaryKey.getGame().getWinnerTeam().equals(winnerTeam);
    boolean hitQuantityGoals = primaryKey.getGame().getGoals().equals(getGoals());

    if (hitExactScore && hitWinnerTeam) {
      points += HIT_EXACT_SCORE_POINTS_AND_WINNER_TEAM;
    }

    if (hitWinnerTeam) {
      points += HIT_WINNER_TEAM;
    }

    if (hitQuantityGoals) {
      points += HIT_QUANTITY_GOALS;
    }
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public BetStatus getStatus() {
    return status;
  }

  public void setStatus(BetStatus status) {
    this.status = status;
  }

  public Team getWinnerTeam() {
    return winnerTeam;
  }

  public void setWinnerTeam(Team winnerTeam) {
    this.winnerTeam = winnerTeam;
  }

  public BetPrimaryKey getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(BetPrimaryKey betPrimaryKey) {
    primaryKey = betPrimaryKey;
  }

  public Integer getPoints() {
    return points;
  }
}

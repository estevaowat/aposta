package com.ewcode.friendsbigball.common.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "player")
public class Player implements Serializable {

  @Serial private static final long serialVersionUID = -1750997515375620037L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public Player() {
    //
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Player team = (Player) o;

    if (id != team.id) {
      return false;
    }
    return name.equals(team.name);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + name.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Player{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}

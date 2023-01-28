package com.ewcode.friendsbigball.common.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "team")
public class Team implements Serializable {

  @Serial private static final long serialVersionUID = -7547870246856534134L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public Team() {
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

    Team team = (Team) o;

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
    return "Team{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}

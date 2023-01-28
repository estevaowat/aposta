package com.ewcode.friendsbigball.costumer.resource.dto;

import jakarta.validation.constraints.NotNull;

public class CreateCostumerDto {

  @NotNull private String name;
  @NotNull private String email;

  public CreateCostumerDto() { //
  }

  public String getName() {
    return name;
  }

  public CreateCostumerDto setName(String name) {
    this.name = name;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public CreateCostumerDto setEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "CreateCostumerDto{" + "name='" + name + '\'' + ", email='" + email + '\'' + '}';
  }
}

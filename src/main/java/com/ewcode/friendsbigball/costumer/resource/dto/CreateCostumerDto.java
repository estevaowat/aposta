package com.ewcode.friendsbigball.costumer.resource.dto;

public class CreateCostumerDto {

  private String name;
  private String email;

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
}

package com.ewcode.friendsbigball.user.resource.dto;

public class CreateUserDto {

    private String name;
    private String email;

    public CreateUserDto() { //
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.andrei.springboot.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequest {

    @NotBlank
    private String username;

    public UserRegisterRequest() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}

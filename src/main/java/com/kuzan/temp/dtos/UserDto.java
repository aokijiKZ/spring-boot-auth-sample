package com.kuzan.temp.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    public UserDto() {
    }
    
    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
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

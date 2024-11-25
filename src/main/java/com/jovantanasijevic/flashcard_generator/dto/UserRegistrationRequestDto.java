package com.jovantanasijevic.flashcard_generator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationRequestDto {
    @NotNull(message = "username is required")
    @Size(min = 3, max = 32, message = "username must be between 3 and 32 characters long")
    private String username;
    @NotNull(message = "password is required")
    @Size(min = 12, message = "password must be at least 12 characters long")
    private String password;
    @NotNull(message = "email is required")
    @Email(message = "invalid email address")
    private String email;

    public UserRegistrationRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

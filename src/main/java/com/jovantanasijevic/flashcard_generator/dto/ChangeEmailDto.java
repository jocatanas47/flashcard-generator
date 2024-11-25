package com.jovantanasijevic.flashcard_generator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ChangeEmailDto {
    @NotBlank(message = "email is required")
    @Email(message = "invalid email address")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

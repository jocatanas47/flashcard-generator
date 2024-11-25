package com.jovantanasijevic.flashcard_generator.dto;

import jakarta.validation.constraints.Email;

public class ChangeEmailDto {
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

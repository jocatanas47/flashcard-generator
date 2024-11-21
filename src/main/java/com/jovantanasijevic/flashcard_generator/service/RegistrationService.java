package com.jovantanasijevic.flashcard_generator.service;

import com.jovantanasijevic.flashcard_generator.dto.UserRegistrationRequestDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {

    }
}

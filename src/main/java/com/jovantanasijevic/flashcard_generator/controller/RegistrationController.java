package com.jovantanasijevic.flashcard_generator.controller;

import com.jovantanasijevic.flashcard_generator.dto.UserRegistrationRequestDto;
import com.jovantanasijevic.flashcard_generator.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public void register(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto
    ) {
        registrationService.registerUser(userRegistrationRequestDto);
    }
}

package com.jovantanasijevic.flashcard_generator.controller;

import com.jovantanasijevic.flashcard_generator.dto.UserRegistrationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @PostMapping("/register")
    public void register(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto
    ) {

    }
}

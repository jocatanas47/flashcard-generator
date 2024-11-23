package com.jovantanasijevic.flashcard_generator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user/change_password")
    public void changePassword()
}

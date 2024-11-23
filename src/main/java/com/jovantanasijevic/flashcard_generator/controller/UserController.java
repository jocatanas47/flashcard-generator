package com.jovantanasijevic.flashcard_generator.controller;

import com.jovantanasijevic.flashcard_generator.dto.ChangePasswordDto;
import com.jovantanasijevic.flashcard_generator.service.UserService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/change_password")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
    }
}

package com.jovantanasijevic.flashcard_generator.controller;

import com.jovantanasijevic.flashcard_generator.dto.ChangeEmailDto;
import com.jovantanasijevic.flashcard_generator.dto.ChangePasswordDto;
import com.jovantanasijevic.flashcard_generator.service.UserService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/user/change_password")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
    }

    @PatchMapping("/user/change_email")
    public void changeEmail(@RequestBody ChangeEmailDto changeEmailDto) {
        userService.changeEmail(changeEmailDto);
    }

    @PostMapping("/user/load_dictionary")
    public void loadDictionary(@RequestBody List<String> words) {

    }
}

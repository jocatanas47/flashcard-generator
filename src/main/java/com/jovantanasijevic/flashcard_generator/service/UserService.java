package com.jovantanasijevic.flashcard_generator.service;

import com.jovantanasijevic.flashcard_generator.dto.ChangePasswordDto;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDetailsManager userDetailsManager;

    public UserService(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        userDetailsManager.changePassword(
                changePasswordDto.getOldPassword(),
                changePasswordDto.getNewPassword()
        );
    }
}

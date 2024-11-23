package com.jovantanasijevic.flashcard_generator.service;

import com.jovantanasijevic.flashcard_generator.domain.User;
import com.jovantanasijevic.flashcard_generator.dto.ChangeEmailDto;
import com.jovantanasijevic.flashcard_generator.dto.ChangePasswordDto;
import com.jovantanasijevic.flashcard_generator.repository.UserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDetailsManager userDetailsManager;
    private final UserRepository userRepository;

    public UserService(UserDetailsManager userDetailsManager, UserRepository userRepository) {
        this.userDetailsManager = userDetailsManager;
        this.userRepository = userRepository;
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        userDetailsManager.changePassword(
                changePasswordDto.getOldPassword(),
                changePasswordDto.getNewPassword()
        );
    }

    public void changeEmail(ChangeEmailDto changeEmailDto) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found")
                );
        user.setEmail(changeEmailDto.getEmail());
        userRepository.save(user);
    }
}

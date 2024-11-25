package com.jovantanasijevic.flashcard_generator.service;

import com.jovantanasijevic.flashcard_generator.domain.User;
import com.jovantanasijevic.flashcard_generator.dto.ChangeEmailDto;
import com.jovantanasijevic.flashcard_generator.dto.ChangePasswordDto;
import com.jovantanasijevic.flashcard_generator.repository.UserRepository;
import com.jovantanasijevic.flashcard_generator.security.IAuthenticationFacade;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDetailsManager userDetailsManager;
    private final UserRepository userRepository;
    private final IAuthenticationFacade authenticationFacade;

    public UserService(UserDetailsManager userDetailsManager, UserRepository userRepository, IAuthenticationFacade authenticationFacade) {
        this.userDetailsManager = userDetailsManager;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        userDetailsManager.changePassword(
                changePasswordDto.getOldPassword(),
                changePasswordDto.getNewPassword()
        );
    }

    public void changeEmail(ChangeEmailDto changeEmailDto) {
        String username = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found")
                );
        user.setEmail(changeEmailDto.getEmail());
        userRepository.save(user);
    }
}

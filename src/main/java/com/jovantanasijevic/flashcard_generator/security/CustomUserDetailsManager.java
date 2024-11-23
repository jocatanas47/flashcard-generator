package com.jovantanasijevic.flashcard_generator.security;

import com.jovantanasijevic.flashcard_generator.domain.User;
import com.jovantanasijevic.flashcard_generator.repository.UserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        if (!(userDetails instanceof CustomUserDetails customUserDetails)) {
            throw new IllegalArgumentException("UserDetails must be an instance of CustomUserDetails");
        }
        User user = customUserDetails.getUser();
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        if (!(userDetails instanceof CustomUserDetails customUserDetails)) {
            throw new IllegalArgumentException("UserDetails must be an instance of CustomUserDetails");
        }
        User newUser = customUserDetails.getUser();
        User oldUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(
                        () -> new IllegalArgumentException("User " + userDetails.getUsername() + " not found")
                );
        newUser.setId(oldUser.getId());
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new IllegalArgumentException("User " + username + " not found")
                );
        userRepository.delete(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new IllegalArgumentException("User " + username + " not found")
                );
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found")
                );
        return new CustomUserDetails(user);
    }
}

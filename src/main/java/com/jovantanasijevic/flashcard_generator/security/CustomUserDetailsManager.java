package com.jovantanasijevic.flashcard_generator.security;

import com.jovantanasijevic.flashcard_generator.domain.User;
import com.jovantanasijevic.flashcard_generator.repository.RoleRepository;
import com.jovantanasijevic.flashcard_generator.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsManager(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

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

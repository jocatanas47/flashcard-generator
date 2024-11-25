package com.jovantanasijevic.flashcard_generator.service;

import com.jovantanasijevic.flashcard_generator.domain.Role;
import com.jovantanasijevic.flashcard_generator.domain.User;
import com.jovantanasijevic.flashcard_generator.dto.UserRegistrationRequestDto;
import com.jovantanasijevic.flashcard_generator.repository.RoleRepository;
import com.jovantanasijevic.flashcard_generator.security.CustomUserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegistrationService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public RegistrationService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        var user = new User();
        user.setUsername(userRegistrationRequestDto.getUsername());
        user.setEmail(userRegistrationRequestDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(
                userRegistrationRequestDto.getPassword()
        ));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            throw new IllegalArgumentException("Role not found");
        }
        var roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);

        userDetailsManager.createUser(new CustomUserDetails(user));
    }
}

package com.jovantanasijevic.flashcard_generator.repository;

import com.jovantanasijevic.flashcard_generator.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

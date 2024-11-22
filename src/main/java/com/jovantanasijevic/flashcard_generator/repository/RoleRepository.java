package com.jovantanasijevic.flashcard_generator.repository;

import com.jovantanasijevic.flashcard_generator.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
    boolean existsByName(String name);
}

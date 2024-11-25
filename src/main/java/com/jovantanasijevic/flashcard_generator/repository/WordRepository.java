package com.jovantanasijevic.flashcard_generator.repository;

import com.jovantanasijevic.flashcard_generator.domain.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findByWordIn(List<String> words);
}

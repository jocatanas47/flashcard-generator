package com.jovantanasijevic.flashcard_generator.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;
}

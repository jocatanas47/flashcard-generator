package com.jovantanasijevic.flashcard_generator.dto;

import java.util.List;

public class ProcessSentencesResponseDto {
    private List<String> notes;
    private List<String> dictionary;

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }
}

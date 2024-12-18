package com.jovantanasijevic.flashcard_generator.dto;

import java.util.List;

public class ProcessSentencesResponseDto {
    private List<NoteDto> notes;
    private List<String> dictionary;

    public List<NoteDto> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDto> notes) {
        this.notes = notes;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }
}

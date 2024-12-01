package com.jovantanasijevic.flashcard_generator.dto;

import java.util.List;

public class ProcessSentencesRequestDto {
    private List<String> sentences;
    private List<String> dictionary;

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }
}

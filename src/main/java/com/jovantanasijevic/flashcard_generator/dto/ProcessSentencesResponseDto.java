package com.jovantanasijevic.flashcard_generator.dto;

import java.util.List;

public class ProcessSentencesResponseDto {
    private List<String> processedSentences;
    private List<String> dictionary;

    public List<String> getProcessedSentences() {
        return processedSentences;
    }

    public void setProcessedSentences(List<String> processedSentences) {
        this.processedSentences = processedSentences;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }
}

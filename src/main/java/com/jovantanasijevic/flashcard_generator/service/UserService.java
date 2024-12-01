package com.jovantanasijevic.flashcard_generator.service;

import com.jovantanasijevic.flashcard_generator.domain.User;
import com.jovantanasijevic.flashcard_generator.domain.Word;
import com.jovantanasijevic.flashcard_generator.dto.ChangeEmailDto;
import com.jovantanasijevic.flashcard_generator.dto.ChangePasswordDto;
import com.jovantanasijevic.flashcard_generator.dto.ProcessSentencesRequestDto;
import com.jovantanasijevic.flashcard_generator.dto.ProcessSentencesResponseDto;
import com.jovantanasijevic.flashcard_generator.repository.UserRepository;
import com.jovantanasijevic.flashcard_generator.repository.WordRepository;
import com.jovantanasijevic.flashcard_generator.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final RestClient restClient;
    private final UserDetailsManager userDetailsManager;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final IAuthenticationFacade authenticationFacade;

    @Value("${sentence.processing.url}")
    private String sentenceProcessingApiUrl;

    public UserService(RestClient restClient, UserDetailsManager userDetailsManager, UserRepository userRepository, WordRepository wordRepository, IAuthenticationFacade authenticationFacade) {
        this.restClient = restClient;
        this.userDetailsManager = userDetailsManager;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        userDetailsManager.changePassword(
                changePasswordDto.getOldPassword(),
                changePasswordDto.getNewPassword()
        );
    }

    public void changeEmail(ChangeEmailDto changeEmailDto) {
        User user = getUser();
        user.setEmail(changeEmailDto.getEmail());
        userRepository.save(user);
    }

    public void loadDictionary(List<String> words) {
        User user = getUser();
        List<Word> newWords = findNewWords(words);

        wordRepository.saveAll(newWords);

        user.getDictionary().addAll(wordRepository.findByWordIn(words));
    }

    @Transactional
    public List<String> processSentences(List<String> sentences) {
        User user = getUser();
        Set<Word> dictionary = user.getDictionary();

        var requestDto = new ProcessSentencesRequestDto();
        requestDto.setSentences(sentences);

        requestDto.setDictionary(dictionary.stream()
                .map(Word::getWord)
                .collect(Collectors.toList()));
        ProcessSentencesResponseDto response = restClient.post()
                .uri(sentenceProcessingApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto)
                .retrieve()
                .body(ProcessSentencesResponseDto.class);

        loadDictionary(response.getDictionary());
        return response.getProcessedSentences();
    }

    private User getUser() {
        String username = authenticationFacade.getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found")
                );
    }

    private List<Word> findNewWords(List<String> words) {
        List<Word> existingWords = wordRepository.findByWordIn(words);

        Set<String> existingWordString = existingWords.stream()
                .map(Word::getWord)
                .collect(Collectors.toSet());

        return words.stream()
                .filter(word -> !existingWordString.contains(word))
                .map(Word::new)
                .toList();
    }
}

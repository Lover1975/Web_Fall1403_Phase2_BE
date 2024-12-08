package com.example.webbackend.controller.services;

import com.example.webbackend.repository.PersonRepository;
import com.example.webbackend.repository.QuestionRepository;
import com.example.webbackend.repository.entity.Person;
import com.example.webbackend.repository.entity.dtos.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final PersonRepository personRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ProfileService(PersonRepository personRepository, QuestionRepository questionRepository) {
        this.personRepository = personRepository;
        this.questionRepository = questionRepository;
    }

    public ProfileDto getProfile(String username) {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        int numberOfDesignedQuestions = questionRepository.findByUsername(username).size();
        int numberOfFollowers = person.getFollowers().size();
        return new ProfileDto(username, numberOfDesignedQuestions, numberOfFollowers);
    }
}

package com.example.webbackend.repository;

import com.example.webbackend.repository.entity.Person;
import com.example.webbackend.repository.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByPerson(Person person);
}

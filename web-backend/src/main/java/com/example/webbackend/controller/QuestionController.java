package com.example.webbackend.controller;

import com.example.webbackend.controller.services.QuestionService;
import com.example.webbackend.repository.entity.Question;
import com.example.webbackend.web.BaseResponse;
import com.example.webbackend.web.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public BaseResponse<List<Question>> getQuestionsByUser(@RequestParam String username) {
        List<Question> questions = questionService.getQuestionsByUser(username);
        return new BaseResponse<>(ResponseHeader.OK, questions);
    }

    @PostMapping
    public BaseResponse<Question> addQuestion(
            @RequestParam String username,
            @RequestParam String questionText,
            @RequestParam String answer1,
            @RequestParam String answer2,
            @RequestParam String answer3,
            @RequestParam String answer4,
            @RequestParam int correctAnswer,
            @RequestParam int hardness,
            @RequestParam String categoryName) {

        Question question = questionService.addQuestion(username, questionText, answer1, answer2, answer3, answer4,
                correctAnswer, hardness, categoryName);
        return new BaseResponse<>(ResponseHeader.OK, question);
    }
}

package com.example.webbackend.controller;

import com.example.webbackend.controller.services.CategoryService;
import com.example.webbackend.controller.services.PersonService;
import com.example.webbackend.controller.services.QuestionService;
import com.example.webbackend.repository.entity.Category;
import com.example.webbackend.repository.entity.Person;
import com.example.webbackend.repository.entity.Question;
import com.example.webbackend.repository.entity.dtos.QuestionDto;
import com.example.webbackend.repository.entity.dtos.QuestionsDto;
import com.example.webbackend.web.BaseResponse;
import com.example.webbackend.web.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RestController
public class QuestionController {

    private final QuestionService questionService;
    private final PersonService personService;
    private final CategoryService categoryService;

    @Autowired
    public QuestionController(QuestionService questionService, PersonService personService, CategoryService categoryService) {
        this.questionService = questionService;
        this.personService = personService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "question-by-user")
    public BaseResponse getQuestionsByUser(@RequestParam String username) {
        Person person = personService.findPersonByUsername(username);
        List<QuestionDto> questions = new LinkedList<QuestionDto>();
        for (Question question : questionService.getQuestionsByPerson(person)) {
            QuestionDto questionDto = new QuestionDto(question.getDesigner().getUsername(), question.getQuestion(), question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4(), question.getCategory().getCategoryName());
            questions.add(questionDto);
        }
        QuestionsDto questionsDto = new QuestionsDto(questions);
        return new BaseResponse<>(ResponseHeader.OK, questionsDto);
    }

    @GetMapping(value = "question-set")
    public BaseResponse getQuestionSet() {
        List<QuestionDto> questionDtos = new LinkedList<QuestionDto>();
        for (Question question : questionService.findAll()) {
            QuestionDto questionDto = new QuestionDto(question.getDesigner().getUsername(), question.getQuestion(), question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4(), question.getCategory().getCategoryName());
            questionDtos.add(questionDto);
        }
        QuestionsDto questionsDto = new QuestionsDto(questionDtos);
        return new BaseResponse<>(ResponseHeader.OK, questionsDto);
    }

    @GetMapping(value = "one-random-question")
    public BaseResponse getOneRandomQuestion() {
        Random rand = new Random();
        List<Question> questions = questionService.findAll();
        Question question = questions.get(rand.nextInt(questions.size()));
        QuestionDto questionDto = new QuestionDto(question.getDesigner().getUsername(), question.getQuestion(), question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4(), question.getCategory().getCategoryName());
        return new BaseResponse<>(ResponseHeader.OK, questionDto);
    }

    @GetMapping(value = "one-random-question-by-category")
    public BaseResponse getOneRandomQuestionByCategory(@RequestParam String categoryName) {
        Category category = categoryService.getCategory(categoryName);
        Random rand = new Random();
        List<Question> questions = questionService.findByCategory(category);
        Question question = questions.get(rand.nextInt(questions.size()));
        QuestionDto questionDto = new QuestionDto(question.getDesigner().getUsername(), question.getQuestion(), question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4(), question.getCategory().getCategoryName());
        return new BaseResponse<>(ResponseHeader.OK, questionDto);
    }

    @PostMapping(value = "add-question")
    public BaseResponse<Question> addQuestion(
            @RequestParam String designer,
            @RequestParam String questionText,
            @RequestParam String answer1,
            @RequestParam String answer2,
            @RequestParam String answer3,
            @RequestParam String answer4,
            @RequestParam int correctAnswer,
            @RequestParam int hardness,
            @RequestParam String categoryName) {
        Person person = personService.findPersonByUsername(designer);
        Question question = questionService.addQuestion(person, questionText, answer1, answer2, answer3, answer4,
                correctAnswer, hardness, categoryName);
        return new BaseResponse<>(ResponseHeader.OK, null);
    }
}

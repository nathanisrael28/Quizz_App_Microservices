package com.nathan.question_service.controller;


import com.nathan.question_service.model.Question;
import com.nathan.question_service.model.QuestionWrapper;
import com.nathan.question_service.model.Response;
import com.nathan.question_service.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return questionService.getAllQuestions();
    }


    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){

     return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public  ResponseEntity<String> addQuestion(@RequestBody @Valid Question question){
        return questionService.addQuestion(question) ;
    }


    /*Generate Question i.e Quiz service will ask the Question service to pick the questions via HTTP request
     here there is no direct interaction between Quiz service to the QuestionDb */
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsforQuiz(@RequestParam("categoryName") String categoryName,
                                                             @RequestParam("numQuestions") Integer numQuestions){

        return questionService.getQuestionsforQuiz(categoryName,numQuestions);
    }

    // Here we are using the postmapping for the getting the data instead of Get why

    //GetQuestions (based on the question id)
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {

        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    // getScore here because all the questions and there right answers are here only, so instead of calculating in the Quiz service
    // It'll be good To calculate here only Which is question service.
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}

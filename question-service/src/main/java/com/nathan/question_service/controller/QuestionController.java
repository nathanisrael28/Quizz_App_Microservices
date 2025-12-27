package com.nathan.question_service.controller;


import com.nathan.question_service.model.Question;
import com.nathan.question_service.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


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

    //GetQuestions (based on the question id)
    // getScore here because all the questions and there right answers are here only, so instead of calculating in the Quiz service
    // It'll be good To calculate here only Which is question service.


}

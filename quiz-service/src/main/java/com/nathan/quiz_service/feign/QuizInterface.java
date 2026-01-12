package com.nathan.quiz_service.feign;

import com.nathan.quiz_service.model.QuestionWrapper;
import com.nathan.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsforQuiz(@RequestParam("categoryName") String categoryName,
                                                             @RequestParam("numQuestions") Integer numQuestions);
    // Here we are using the postmapping for the getting the data instead of Get why

    //GetQuestions (based on the question id)
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer> questionIds);

    // getScore here because all the questions and there right answers are here only, so instead of calculating in the Quiz service
    // It'll be good To calculate here only Which is question service.
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}

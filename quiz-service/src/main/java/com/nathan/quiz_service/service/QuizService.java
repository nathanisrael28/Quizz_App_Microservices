package com.nathan.quiz_service.service;


import com.nathan.quiz_service.feign.QuizInterface;
import com.nathan.quiz_service.model.QuestionWrapper;
import com.nathan.quiz_service.model.Quiz;
import com.nathan.quiz_service.model.Response;
import com.nathan.quiz_service.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
  /*      List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);*/

        //Here we have to call the question url eg: http://localhost:8080/question/generate
        // it can be called using RestTemplate - it offers a manual proccess of assigning the all the service properites
        // like url, port, apis, etc. so its not a good idea to hardcode them, so that why we are using the Open Feign client
        // so that it will detect the service according to the api or method name that we are calling.
        List<Integer> questions = quizInterface.getQuestionsforQuiz(category,numQ).getBody(); //using getbody() because it will return the response entity
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {

       Quiz quiz = quizRepo.findById(quizId).get();
       List<Integer> questionIds= quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResults(Integer quizId, List<Response> responses) {
      return  quizInterface.getScore(responses);
    }
}

package com.nathan.quiz_service.service;


import com.nathan.quiz_service.model.Question;
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
   /* @Autowired
    private QuestionRepo questionRepo;*/

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);

        try {
            return new ResponseEntity<>("Quiz Created Sucessfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Quiz Not Created", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {

        Optional<Quiz> quiz = quizRepo.findById(quizId);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        for(Question q :questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUsers.add(qw);
        }

        return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResults(Integer quizId, List<Response> responses) {

        Quiz quiz = quizRepo.findById(quizId).get();
        //We can use dot get To avoid the optional Object Since it's for learning, But we should use optional
        //Because it will To find the null or empty And handle the exception

        List<Question> questions = quiz.getQuestions();
        int result=0;
        int i =0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                result++;
            }
            i++;
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}

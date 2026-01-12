package com.nathan.question_service.service;


import com.nathan.question_service.model.Question;
import com.nathan.question_service.model.QuestionWrapper;
import com.nathan.question_service.model.Response;
import com.nathan.question_service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
    try{
        return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
    }
        catch (Exception e)
    {
        e.printStackTrace();
    }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("Question added Successfully",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question Not Added",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsforQuiz(String categoryName, Integer numQuestions) {

        // method 1 - if the query returns Questions in Repo Layer
//        List<Question> questions = questionRepo.findRandomQuestionsByCategory(categoryName,numQuestions);
//        //method Reference
//        List<Integer> questionIds = questions.stream().map(Question::getId).toList();
//        //using lambda exp
//        //List<Integer> questionIds = questions.stream().map(q -> q.getId()).toList();

        // Method -2 if query return just question ids
        List<Integer> questionIds = questionRepo.findRandomQuestionsByCategory(categoryName,numQuestions);

        return new ResponseEntity<>(questionIds,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for(Integer id:questionIds){
            questions.add(questionRepo.findById(id).get());
        }

        for(Question q:questions){
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(q.getId());
            qw.setQuestionTitle(q.getQuestionTitle());
            qw.setOption1(q.getOption1());
            qw.setOption2(q.getOption2());
            qw.setOption3(q.getOption3());
            qw.setOption4(q.getOption4());
            wrappers.add(qw);

        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int result=0;
        for(Response response : responses){
            Question question = questionRepo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                result++;
            }
        }
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}

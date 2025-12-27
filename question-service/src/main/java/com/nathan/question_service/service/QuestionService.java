package com.nathan.question_service.service;


import com.nathan.question_service.model.Question;
import com.nathan.question_service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}

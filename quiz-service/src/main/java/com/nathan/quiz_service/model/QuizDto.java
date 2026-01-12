package com.nathan.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    private String categoryName;
    int numQuestions;
    private String title;
}

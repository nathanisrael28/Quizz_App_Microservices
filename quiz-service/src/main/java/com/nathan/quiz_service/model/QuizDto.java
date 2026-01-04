package com.nathan.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    int numQ;
    private String title;
}

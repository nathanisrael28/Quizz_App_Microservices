package com.nathan.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    //here we are changing the that instead of getting the questions, we are getting the Question ids,
    // since we are getting the numbers which is Integers not an objects we can't use @ManytoMany, instead we
    // use @ElementCollection
    @ElementCollection
    private List<Integer> questionIds;
}

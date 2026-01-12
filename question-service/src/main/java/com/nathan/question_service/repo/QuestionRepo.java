package com.nathan.question_service.repo;

import com.nathan.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

//    @Query(value = "SELECT * FROM question q WHERE q.category= :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
//    List<Question> findRandomQuestionsByCategory(@Param("category") String category,@Param("numQ") int numQ);


    @Query(value = "SELECT q.id FROM question q WHERE q.category= :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(@Param("category") String category,@Param("numQ") int numQ);

}

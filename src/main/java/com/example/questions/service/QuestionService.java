package com.example.questions.service;

import com.example.questions.model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();

    List<Question> getQuestionsByCategory(String category);

    List<Question> getQuestionsByDifficulty(String difficulty);

    Question create(String questionTitle, String option1, String option2, String option3, String rightAnswer, String category, String difficulty);

    Question update(Integer id, Question question);

    void delete(Integer id);
}

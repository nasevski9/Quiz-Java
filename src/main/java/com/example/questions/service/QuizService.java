package com.example.questions.service;

import com.example.questions.model.QuestionWrapper;
import com.example.questions.model.Quiz;
import com.example.questions.model.Response;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(String category, int numQ, String title);

    List<QuestionWrapper> getQuizQuestions(Integer id);

    Integer calculateResult(Integer id, List<Response> responses);

    void deleteQuiz(Integer id);
}

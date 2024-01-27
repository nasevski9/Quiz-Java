package com.example.questions.service.impl;

import com.example.questions.model.Question;
import com.example.questions.model.QuestionWrapper;
import com.example.questions.model.Quiz;
import com.example.questions.model.Response;
import com.example.questions.repository.QuestionRepository;
import com.example.questions.repository.QuizRepository;
import com.example.questions.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Quiz createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);
        return quiz;
    }

    @Override
    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3());
            questionsForUser.add(qw);
        }
        return questionsForUser;
    }

    @Override
    public Integer calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int rightAnswer = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                rightAnswer++;
            }
            i++;
        }
        return rightAnswer;
    }

    @Override
    public void deleteQuiz(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException();
        }
        quizRepository.deleteById(id);
    }
}

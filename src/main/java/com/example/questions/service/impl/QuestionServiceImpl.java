package com.example.questions.service.impl;

import com.example.questions.model.Question;
import com.example.questions.repository.QuestionRepository;
import com.example.questions.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        if (category.isEmpty()){
            throw new IllegalArgumentException();
        }
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> getQuestionsByDifficulty(String difficulty) {
        if (difficulty.isEmpty()){
            throw new IllegalArgumentException();
        }
        return questionRepository.findByDifficulty(difficulty);
    }

    @Override
    public Question create(String questionTitle, String option1, String option2, String option3, String rightAnswer, String category, String difficulty) {

        Question newQuestion = new Question(questionTitle, option1, option2, option3, rightAnswer, category, difficulty);

        if (questionTitle.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || rightAnswer.isEmpty() || difficulty.isEmpty()){
            throw new IllegalArgumentException();
        }
        questionRepository.save(newQuestion);
        return newQuestion;
    }

    @Override
    public Question update(Integer id, Question question) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        Optional<Question> existingQuestion = questionRepository.findById(id);

        if (existingQuestion.isPresent()){
            Question updatedQuestion = new Question();
            updatedQuestion.setQuestionTitle(question.getQuestionTitle());
            updatedQuestion.setOption1(question.getOption1());
            updatedQuestion.setOption2(question.getOption2());
            updatedQuestion.setOption3(question.getOption3());
            updatedQuestion.setRightAnswer(question.getRightAnswer());
            updatedQuestion.setCategory(question.getCategory());
            updatedQuestion.setDifficulty(question.getDifficulty());

            return questionRepository.save(updatedQuestion);
        }
        else {
            return null;
        }

    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException();
        }
        questionRepository.deleteById(id);
    }
}

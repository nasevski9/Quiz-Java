package com.example.questions.controller;

import com.example.questions.model.Question;
import com.example.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        try {
            questionService.getQuestionsByCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByDifficulty(@PathVariable String difficulty){
        try {
            questionService.getQuestionsByDifficulty(difficulty);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Question> create(@RequestBody Question question){
         try {
             Question createdQueation = questionService.create(question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getRightAnswer(), question.getCategory(), question.getDifficulty());
             return new ResponseEntity<>(createdQueation, HttpStatus.OK);
         } catch (IllegalArgumentException e) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> update(@PathVariable Integer id, @RequestBody Question question) {
        try {
            questionService.update(id, question);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        questionService.delete(id);
    }
}

package com.vasyl.summer.practice.controller;

import com.vasyl.summer.practice.database.entity.UserQuestion;
import com.vasyl.summer.practice.models.UserQuestionDto;
import com.vasyl.summer.practice.service.UserQuestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user-question")
@RequiredArgsConstructor
public class UserQuestionController {

    private final UserQuestionService userQuestionService;

    @GetMapping
    public List<UserQuestion> getAllUserQuestions(){
        return userQuestionService.getAllUserQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuestion> getUserQuestionById(@PathVariable String id){
        UserQuestion question = userQuestionService.getUserQuestionById(id);

        return ResponseEntity.ok(question);
    }
    
    @PostMapping
    public void createUserQuestion(@RequestBody UserQuestionDto userQuestionDto){
        userQuestionService.createUserQuestion(userQuestionDto);
    }

}

package com.vasyl.summer.practice.controller;

import com.vasyl.summer.practice.database.entity.UserFeedback;
import com.vasyl.summer.practice.models.UserFeedbackDto;
import com.vasyl.summer.practice.service.UserFeedbackService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user-feedback")
@RequiredArgsConstructor
public class UserFeedbackController {

    private final UserFeedbackService userFeedbackService;

    @GetMapping
    public List<UserFeedback> getAllUserFeedbacks(){
        return userFeedbackService.getAllUserFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFeedback> getUserFeedbackById(@PathVariable String id){
        UserFeedback feedback = userFeedbackService.getUserFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }
    
    @PostMapping
    public void createUserFeedback(@RequestBody UserFeedbackDto userFeedbackDto){
        userFeedbackService.createUserFeedback(userFeedbackDto);
    }
}

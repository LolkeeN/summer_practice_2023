package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.dao.UserDao;
import com.vasyl.summer.practice.database.entity.UserFeedback;
import com.vasyl.summer.practice.database.repository.UserFeedbackRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.models.UserFeedbackDto;
import com.vasyl.summer.practice.models.UserQuestionDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFeedbackService {

    private final UserFeedbackRepository userFeedbackRepository;
    private final UserDao userDao;

    public void createUserFeedback(UserFeedbackDto dto){
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUser(userDao.getUserById(dto.getUserId()));
        userFeedback.setText(dto.getText());
        userFeedbackRepository.save(userFeedback);
    }

    public List<UserFeedback> getAllUserFeedbacks(){
        return (List<UserFeedback>) userFeedbackRepository.findAll();
    }

    public UserFeedback getUserFeedbackById(String id){
        return userFeedbackRepository.findById(id).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.USER_QUESTION_NOT_FOUND)
        );
    }
}

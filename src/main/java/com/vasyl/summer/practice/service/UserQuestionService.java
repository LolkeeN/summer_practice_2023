package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.dao.UserDao;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.entity.UserQuestion;
import com.vasyl.summer.practice.database.repository.UserQuestionRepository;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.models.UserQuestionDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQuestionService {

    private final UserQuestionRepository userQuestionRepository;
    private final UserDao userDao;

    public void createUserQuestion(UserQuestionDto dto){
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setUser(userDao.getUserById(dto.getUserId()));
        userQuestion.setText(dto.getText());
        userQuestion.setAnswered(false);
        userQuestionRepository.save(userQuestion);
    }

    public List<UserQuestion> getAllUserQuestions(){
        return (List<UserQuestion>) userQuestionRepository.findAll();
    }

    public UserQuestion getUserQuestionById(String id){
        return userQuestionRepository.findById(id).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.USER_QUESTION_NOT_FOUND)
        );
    }

    public void answerUserQuestion(String id, String text){
        UserQuestion userQuestionById = getUserQuestionById(id);
        userQuestionById.setText(text);
        userQuestionById.setAnswered(true);
        userQuestionRepository.save(userQuestionById);
    }

    public List<UserQuestion> getAllUnansweredQuestions(){
        return userQuestionRepository.getAllByAnsweredIsFalse();
    }
}

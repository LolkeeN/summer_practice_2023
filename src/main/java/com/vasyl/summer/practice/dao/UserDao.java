package com.vasyl.summer.practice.dao;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    @InvocationLog
    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.USER_NOT_FOUND_EXCEPTION)
        );
    }

    @InvocationLog
    public User getUserByEmail(String email){
        return userRepository.getByEmail(email).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.USER_NOT_FOUND_EXCEPTION)
        );
    }
}

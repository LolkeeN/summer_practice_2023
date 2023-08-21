package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.dao.UserDao;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserBannedStatus;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.mapper.UserMapper;
import com.vasyl.summer.practice.models.AdminUserDto;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vasyl.summer.practice.exceptions.InternalViolationType.INVALID_PASSWORD_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @InvocationLog
    @Transactional
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @InvocationLog
    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @InvocationLog
    public Page<AdminUserDto> searchUserByEmail(String email, Pageable pageable){
        Page<User> page = userRepository.getByEmailLikeIgnoreCase(email, pageable);
        return new PageImpl<>(
                page.stream()
                        .map(UserMapper::toAdminUserDto)
                        .collect(Collectors.toList()),
                pageable,
                page.getTotalElements()
        );
    }

    @InvocationLog
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @InvocationLog
    public void changePassword(String userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);

        validPassword(newPassword);
        validPassword(user, oldPassword);

        user.setPassword(passwordEncoder.encode(newPassword));
        save(user);
        log.debug("Success change password for user: {}", user.getEmail());
    }

    @InvocationLog
    public void setNewPassword(User user, String newPassword) {
        validPassword(newPassword);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        log.debug("Success change password for user: {}", user.getEmail());
    }

    @InvocationLog
    public void validPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.error("Entered password for user with id {} is invalid", user.getId());
            throw new InternalViolationException(INVALID_PASSWORD_EXCEPTION);
        }
    }

    @InvocationLog
    public void validPassword(String password) {
        if (password.length() < 4) {
            log.error("Password length must be 4 and more");
            throw new InternalViolationException(INVALID_PASSWORD_EXCEPTION);
        }
    }
    @InvocationLog
    public void banUser(String userId){
        User userById = getUserById(userId);
        userById.setBannedStatus(UserBannedStatus.BANNED);
        save(userById);
    }
    @InvocationLog
    public void unbanUser(String userId){
        User userById = getUserById(userId);
        userById.setBannedStatus(UserBannedStatus.NOT_BANNED);
        save(userById);
    }

    @InvocationLog
    public void deleteUser(String userId){
        User userById = getUserById(userId);
        userById.setIsDeleted(true);
        save(userById);
    }

}

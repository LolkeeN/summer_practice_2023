package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.mapper.UserMapper;
import com.vasyl.summer.practice.service.UserService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final UserService userService;


    @InvocationLog
    public Page<AdminUserDto> getUsersByPartnerId(String partnerId, Pageable pageable) {
        var page = userRepository.getUsersByPartnerId(partnerId, pageable);
        log.debug("PartnerId: {}. Page retrieved from repository: {}", partnerId, page);
        return new PageImpl<>(
                page.stream().map(UserMapper::toAdminUserDto).collect(Collectors.toList()),
                pageable,
                page.getTotalElements()
        );
    }

    @InvocationLog
    public Page<AdminUserDto> getAllUsers(Pageable pageable) {
        var page = userRepository.getAllUsers(pageable);
        log.debug("Page retrieved from repository: {}", page);
        return new PageImpl<>(
                page.stream().map(UserMapper::toAdminUserDto).collect(Collectors.toList()),
                pageable,
                page.getTotalElements()
        );
    }

    @InvocationLog
    public void changeUserRole(String userId, UserRole role) {
        var user = userService.getUserById(userId);
        user.setRole(role);
        log.debug("UserId: {}. New role: {}", userId, role);
        userService.save(user);
    }
}

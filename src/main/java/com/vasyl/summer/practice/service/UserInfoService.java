package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.repository.ActivityRepository;
import com.vasyl.summer.practice.mapper.ActivityMapper;
import com.vasyl.summer.practice.mapper.UserMapper;
import com.vasyl.summer.practice.models.ActivityResponseDto;
import com.vasyl.summer.practice.models.AdminUserDto;
import com.vasyl.summer.practice.models.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserService userService;
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public UserDto getUserDto(String userId) {
        var user = userService.getUserById(userId);
        return UserMapper.toDto(user);
    }

    @InvocationLog
    public AdminUserDto getAdminUserDto(String userId) {
        var user = userService.getUserById(userId);
        return UserMapper.toAdminUserDto(user);
    }

    @InvocationLog
    public List<ActivityResponseDto> getAllUserActivities(String userId){
        User userById = userService.getUserById(userId);
        return activityRepository.getAllByUsersContaining(userById).stream()
                .map(activityMapper::toActivityResponseDto)
                .toList();
    }
}

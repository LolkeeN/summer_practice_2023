package com.vasyl.summer.practice.mapper;


import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.models.AdminUserDto;
import com.vasyl.summer.practice.models.UserDto;
import com.vasyl.summer.practice.models.UserResponseDto;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    @InvocationLog
    public static UserDto toDto(User user) {
        var dto = new UserDto();
        dto.setCreated(user.getCreated());
        dto.setId(user.getId());
        dto.setPartnerId(user.getPartnerId());
        dto.setEmail(user.getEmail());
        dto.setIsAdmin(user.getRole().equals(UserRole.ADMIN) || user.getRole().equals(UserRole.SUPER_ADMIN));
        return dto;
    }

    @InvocationLog
    public static AdminUserDto toAdminUserDto(User user) {
        var dto = new AdminUserDto();
        dto.setUserId(user.getId());
        dto.setCreated(user.getCreated());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setUserRegistrationStatus(user.getRegistrationStatus());
        dto.setUserBannedStatus(user.getBannedStatus());
        return dto;
    }
    @InvocationLog
    public static UserResponseDto toUserResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setRole(user.getRole());
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCreated(user.getCreated());
        dto.setAccountId(user.getAccountId());
        dto.setRegistrationStatus(user.getRegistrationStatus());
        dto.setBannedStatus(user.getBannedStatus());
        dto.setIsDeleted(user.getIsDeleted());

        return dto;
    }

    @InvocationLog
    public static List<UserResponseDto> toUserResponseDtoList(List<User> users){
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user:users) {
            userResponseDtos.add(toUserResponseDto(user));
        }
        return userResponseDtos;
    }
}

package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminAuthDetailsDto {

    private String userId;
    private String accessToken;
    private final String tokenType;

    private UserRole userRole;

    public AdminAuthDetailsDto(String userId, String accessToken, UserRole userRole) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "AdminAuthDetailsDto{" +
                "userId=" + userId +
                ", accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

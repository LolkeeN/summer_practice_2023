package com.vasyl.summer.practice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vasyl.summer.practice.database.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDetailsDto {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private UserRole role;
    private String tokenType;

    private Boolean twoFa;
    private Boolean mailTwoFa;
    private String securityToken;

    public AuthDetailsDto(String userId, String accessToken, UserRole role) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.role = role;
        this.tokenType = "Bearer";
    }

    public AuthDetailsDto(Boolean twoFa, Boolean mailTwoFa) {
        this.twoFa = twoFa;
        this.mailTwoFa = mailTwoFa;
    }

    @Override
    public String toString() {
        return "AuthDetailsDto{" +
                "userId='" + userId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}


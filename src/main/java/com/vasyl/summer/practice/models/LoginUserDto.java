package com.vasyl.summer.practice.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String deviceId;

    public LoginUserDto() {
    }

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

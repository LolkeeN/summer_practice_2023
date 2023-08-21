package com.vasyl.summer.practice.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class RegistrationUserDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    public RegistrationUserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

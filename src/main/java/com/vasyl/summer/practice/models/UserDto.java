package com.vasyl.summer.practice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String id;
    private Long created;
    private String partnerId;
    private String email;
    private Boolean isAdmin;
    private Boolean twoFaLogin;
    private Boolean mailConfirmLogin;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", created=" + created +
                ", email='" + email + '\'' +
                ", twoFaLogin=" + twoFaLogin +
                ", mailConfirmLogin=" + mailConfirmLogin +
                '}';
    }
}

package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.enums.UserBannedStatus;
import com.vasyl.summer.practice.database.enums.UserRegistrationStatus;
import com.vasyl.summer.practice.database.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserDto {

    private String userId;
    private Long created;
    private UserRole role;
    private String email;
    private UserRegistrationStatus userRegistrationStatus;
    private UserBannedStatus userBannedStatus;
    private Boolean twoFaLogin;
    private Boolean mailConfirmLogin;
    private Long lastEntry;
    private String country;

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", created=" + created +
                ", email='" + email + '\'' +
                ", twoFaLogin=" + twoFaLogin +
                ", mailConfirmLogin=" + mailConfirmLogin +
                '}';
    }
}

package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.enums.UserBannedStatus;
import com.vasyl.summer.practice.database.enums.UserRegistrationStatus;
import com.vasyl.summer.practice.database.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private String id;

    private Long created;

    private String email;

    private UserRegistrationStatus registrationStatus;

    private UserBannedStatus bannedStatus;

    private UserRole role;
    private Boolean isDeleted = false;
    private Long accountId;
}

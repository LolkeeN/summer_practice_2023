package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.entity.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFeedbackDto {
    private String text;
    private String userId;
}

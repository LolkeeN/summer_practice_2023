package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionDto {

    private String text;
    private String userId;
}

package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.entity.Section;
import com.vasyl.summer.practice.database.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityResponseDto {

    private String id;

    private Date date;
    private int startTime;
    private int endTime;
    private Long created;
    private List<UserResponseDto> users;
}

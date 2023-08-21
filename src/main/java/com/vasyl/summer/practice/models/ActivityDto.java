package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.entity.Section;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDto {

    private Date date;
    private int startTime;
    private int endTime;
    private List<String> userIds;
}

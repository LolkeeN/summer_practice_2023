package com.vasyl.summer.practice.mapper;

import com.vasyl.summer.practice.database.entity.Activity;
import com.vasyl.summer.practice.database.entity.Section;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.repository.SectionRepository;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.models.ActivityDto;
import com.vasyl.summer.practice.models.ActivityResponseDto;
import com.vasyl.summer.practice.models.SectionResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityMapper {

    private final UserRepository userRepository;
    public void mapActivityData(ActivityDto dto, Activity activity){
        activity.setDate(dto.getDate());
        activity.setEndTime(dto.getEndTime());
        activity.setStartTime(dto.getStartTime());
        activity.setUsers((List<User>) userRepository.findAllById(dto.getUserIds()));
    }

    public ActivityResponseDto toActivityResponseDto(Activity activity){
        ActivityResponseDto dto = new ActivityResponseDto();
        dto.setDate(activity.getDate());
        dto.setId(activity.getId());
        dto.setCreated(activity.getCreated());
        dto.setStartTime(activity.getStartTime());
        dto.setEndTime(activity.getEndTime());
        dto.setUsers(UserMapper.toUserResponseDtoList(activity.getUsers()));
        return dto;
    }
}

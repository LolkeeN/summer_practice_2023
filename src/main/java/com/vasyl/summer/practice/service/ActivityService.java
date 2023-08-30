package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.dao.UserDao;
import com.vasyl.summer.practice.database.entity.Activity;
import com.vasyl.summer.practice.database.entity.Section;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.repository.ActivityRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.mapper.ActivityMapper;
import com.vasyl.summer.practice.mapper.SectionMapper;
import com.vasyl.summer.practice.models.ActivityDto;
import com.vasyl.summer.practice.models.ActivityResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final UserDao userDao;
    private final SectionMapper sectionMapper;
    private final SectionService sectionService;

    public List<ActivityResponseDto> getAllActivities() {
        List<Activity> activities = (List<Activity>) activityRepository.findAll();

        return activities.stream()
                .map(activityMapper::toActivityResponseDto)
                .toList();
    }

    public ActivityResponseDto getActivityResponseDtoByActivityId(String activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.ACTIVITY_NOT_FOUND)
        );
        return activityMapper.toActivityResponseDto(activity);
    }

    public void updateActivityById(String id, ActivityDto activityDto) {
        Activity activity = activityRepository.findById(id).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.ACTIVITY_NOT_FOUND)
        );
        activityMapper.mapActivityData(activityDto, activity);
        activityRepository.save(activity);
    }

    public void createActivity(ActivityDto dto) {
        Activity activity = new Activity();
        activityMapper.mapActivityData(dto, activity);
        Section section = sectionService.getSectionById(dto.getSectionId());
        activityRepository.save(activity);
        List<Activity> activities = section.getActivities();
        activities.add(activity);
        section.setActivities(activities);
        sectionService.save(section);
    }

    public void deleteActivityById(String id) {
        activityRepository.deleteById(id);
    }

    public void addUserToActivityUsers(String userId, String activityId){
        Activity activity = activityRepository.findById(activityId).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.ACTIVITY_NOT_FOUND)
        );
        User user = userDao.getUserById(userId);
        activity.getUsers().add(user);
        activityRepository.save(activity);
    }
}

package com.vasyl.summer.practice.controller;

import com.vasyl.summer.practice.annotation.UserId;
import com.vasyl.summer.practice.models.ActivityDto;
import com.vasyl.summer.practice.models.ActivityResponseDto;
import com.vasyl.summer.practice.service.ActivityService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public List<ActivityResponseDto> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDto> getActivityById(@PathVariable String id) {
        return ResponseEntity.ok(activityService.getActivityResponseDtoByActivityId(id));
    }

    @PostMapping
    public void createActivity(@RequestBody ActivityDto activityDto) {
        activityService.createActivity(activityDto);
    }

    @PutMapping("/{id}")
    public void updateActivityById(@PathVariable String id, ActivityDto activityDto) {
        activityService.updateActivityById(id, activityDto);
    }

    @DeleteMapping("/{id}")
    public void deleteActivityById(@PathVariable String id) {
        activityService.deleteActivityById(id);
    }

    @PostMapping("/{activityId}")
    public void attendActivity(@PathVariable String activityId, @Parameter(hidden = true) @UserId String userId) {
        activityService.addUserToActivityUsers(userId, activityId);
    }
}

package com.vasyl.summer.practice.controller.user;

import com.vasyl.summer.practice.annotation.UserId;
import com.vasyl.summer.practice.models.ActivityResponseDto;
import com.vasyl.summer.practice.models.UserDto;
import com.vasyl.summer.practice.service.UserInfoService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN', 'USER')")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/info")
    public ResponseEntity<UserDto> getUserInfo(@Parameter(hidden = true) @UserId String userId) {
        return ResponseEntity.ok(userInfoService.getUserDto(userId));
    }

    @GetMapping("/activities")
    public List<ActivityResponseDto> getUserActivities(@Parameter(hidden = true) @UserId String userId){
        return userInfoService.getAllUserActivities(userId);
    }
}

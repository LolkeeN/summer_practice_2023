package com.vasyl.summer.practice.controller.user;

import com.vasyl.summer.practice.annotation.ControllerInvocationLog;
import com.vasyl.summer.practice.annotation.UserId;
import com.vasyl.summer.practice.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN', 'USER')")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ControllerInvocationLog
    public ResponseEntity<?> getUser(@Parameter(hidden = true) @UserId String userId) {
        return ResponseEntity.ok(userId);
    }

    @ControllerInvocationLog
    @PutMapping("/change_password")
    public void processChangePasswordRequest(@RequestParam String oldPassword, @RequestParam String newPassword,
            @Parameter(hidden = true) @UserId String userId) {
        userService.changePassword(userId, oldPassword, newPassword);
    }

}

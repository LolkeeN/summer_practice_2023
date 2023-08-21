package com.vasyl.summer.practice.controller.admin;

import com.vasyl.summer.practice.annotation.ControllerInvocationLog;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.models.AdminUserDto;
import com.vasyl.summer.practice.models.AdminUserService;
import com.vasyl.summer.practice.service.UserInfoService;
import com.vasyl.summer.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/super-admin/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SUPER_ADMIN')")
public class SuperAdminUserController {

    private final AdminUserService adminUserService;
    private final UserInfoService userInfoService;
    private final UserService userService;


    @GetMapping("/user")
    @ControllerInvocationLog
    public ResponseEntity<?> getUserById(@RequestParam String userId) {
        return ResponseEntity.ok(userInfoService.getAdminUserDto(userId));
    }

    @PutMapping
    @ControllerInvocationLog
    public void changeUserRole(@RequestParam String userId, @RequestParam UserRole newRole) {
        adminUserService.changeUserRole(userId, newRole);
    }

    @GetMapping("/get_all")
    @ControllerInvocationLog
    public ResponseEntity<?> getAllUsers(@ParameterObject Pageable pageable) {
        var result = adminUserService.getAllUsers(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/by_email")
    @ControllerInvocationLog
    public ResponseEntity<Page<AdminUserDto>> searchUserByEmail(@RequestParam String email, @ParameterObject Pageable pageable) {
        Page<AdminUserDto> userByEmail = userService.searchUserByEmail(email, pageable);
        return ResponseEntity.ok(userByEmail);
    }
}

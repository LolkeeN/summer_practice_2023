package com.vasyl.summer.practice.controller.admin;

import com.vasyl.summer.practice.annotation.ControllerInvocationLog;
import com.vasyl.summer.practice.annotation.UserId;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.mapper.UserMapper;
import com.vasyl.summer.practice.models.AdminUserDto;
import com.vasyl.summer.practice.models.AdminUserService;
import com.vasyl.summer.practice.service.UserInfoService;
import com.vasyl.summer.practice.service.UserQuestionService;
import com.vasyl.summer.practice.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.vasyl.summer.practice.exceptions.InternalViolationType.NOT_ENOUGH_RIGHTS_NEED_SUPER_ADMIN;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final UserInfoService userInfoService;
    private final UserService userService;
    private final UserQuestionService userQuestionService;

    @GetMapping("/user")
    @ControllerInvocationLog
    public ResponseEntity<?> getUserById(@RequestParam String userId, @Parameter(hidden = true) @UserId String adminId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toAdminUserDto(user));
    }

    @PutMapping
    @ControllerInvocationLog
    public void changeUserRole(@RequestParam String userId, @RequestParam UserRole newRole,
            @Parameter(hidden = true) @UserId String adminId) {
        if (newRole.equals(UserRole.SUPER_ADMIN)) {
            throw new InternalViolationException(NOT_ENOUGH_RIGHTS_NEED_SUPER_ADMIN);
        }

        adminUserService.changeUserRole(userId, newRole);
    }

    @GetMapping("/get_all")
    @ControllerInvocationLog
    public ResponseEntity<?> getAllUsers(@ParameterObject Pageable pageable, @Parameter(hidden = true) @UserId String adminId) {
        User admin = userService.getUserById(adminId);
        var result = adminUserService.getUsersByPartnerId(admin.getPartnerId(), pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/by_email")
    @ControllerInvocationLog
    public ResponseEntity<Page<AdminUserDto>> searchUserByEmail(@RequestParam String email, @ParameterObject Pageable pageable) {
        Page<AdminUserDto> userByEmail = userService.searchUserByEmail(email, pageable);
        return ResponseEntity.ok(userByEmail);
    }

    @PostMapping("/{userId}/ban")
    @ControllerInvocationLog
    public void banUser(@PathVariable String userId) {
        userService.banUser(userId);
    }

    @PostMapping("/{userId}/unban")
    @ControllerInvocationLog
    public void unbanUser(@PathVariable String userId) {
        userService.unbanUser(userId);
    }

    @DeleteMapping("/{userId}")
    @ControllerInvocationLog
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/{id}/answer")
    public void answerQuestion(@PathVariable String id, @RequestParam String text){
        userQuestionService.answerUserQuestion(id, text);
    }
}

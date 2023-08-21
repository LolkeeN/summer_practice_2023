package com.vasyl.summer.practice.controller.admin;

import com.vasyl.summer.practice.annotation.UserId;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.models.LoginAdminDto;
import com.vasyl.summer.practice.service.AdminAuthService;
import com.vasyl.summer.practice.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/auth/login")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService authService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginAdminDto dto) {
        return ResponseEntity.ok(authService.auth(dto));
    }

    @GetMapping
    public ResponseEntity<?> isAdmin(@Parameter(hidden = true) @UserId String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user.getRole().equals(UserRole.ADMIN));
    }

}

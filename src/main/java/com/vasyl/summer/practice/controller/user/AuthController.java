package com.vasyl.summer.practice.controller.user;

import com.vasyl.summer.practice.annotation.ControllerInvocationLog;
import com.vasyl.summer.practice.models.LoginUserDto;
import com.vasyl.summer.practice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ControllerInvocationLog
    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto dto, HttpServletRequest request) {
        return ResponseEntity.ok(authService.auth(dto, request));
    }

    @ControllerInvocationLog
    @PutMapping
    public ResponseEntity<?> checkLogin(@Valid @RequestBody LoginUserDto dto) {
        return ResponseEntity.ok(authService.checkLogin(dto));
    }

}

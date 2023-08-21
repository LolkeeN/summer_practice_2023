package com.vasyl.summer.practice.controller.user;

import com.vasyl.summer.practice.annotation.ControllerInvocationLog;
import com.vasyl.summer.practice.models.AuthDetailsDto;
import com.vasyl.summer.practice.models.RegistrationUserDto;
import com.vasyl.summer.practice.service.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @ControllerInvocationLog
    @PostMapping
    public ResponseEntity<AuthDetailsDto> registration(@Valid @RequestBody RegistrationUserDto data,
            HttpServletRequest request) {
            registrationService.register(data);
            return null;
    }

}

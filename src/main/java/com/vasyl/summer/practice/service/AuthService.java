package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.configs.security.JwtUserDetailsService;
import com.vasyl.summer.practice.configs.security.jwt.JwtTokenProvider;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserBannedStatus;
import com.vasyl.summer.practice.database.enums.UserRegistrationStatus;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.models.AuthDetailsDto;
import com.vasyl.summer.practice.models.LoginUserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.vasyl.summer.practice.exceptions.InternalViolationType.EMAIL_NOT_CONFIRM_EXCEPTION;
import static com.vasyl.summer.practice.exceptions.InternalViolationType.INVALID_PASSWORD_EXCEPTION;
import static com.vasyl.summer.practice.exceptions.InternalViolationType.USER_BANNED;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService jwtUserDetailsService;

    public AuthDetailsDto auth(LoginUserDto dto, HttpServletRequest request) {
        User user = userService.getUserByEmail(dto.getEmail().toLowerCase());
        log.debug("Found user: {}", user);
        validUser(user);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getId(),
                            dto.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalViolationException(INVALID_PASSWORD_EXCEPTION);
        }

        String token = jwtTokenProvider.createToken(user.getId());
        jwtUserDetailsService.saveUserToken(user, token);

        userService.save(user);
        return new AuthDetailsDto(user.getId(), token, user.getRole());
    }
    @InvocationLog
    public String checkLogin(LoginUserDto dto) {
        User user = userService.getUserByEmail(dto.getEmail().toLowerCase());
        validUser(user);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getId(),
                            dto.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalViolationException(INVALID_PASSWORD_EXCEPTION);
        }
        return user.getId();
    }

    @InvocationLog
    private void validUser(User user) {
        if (user.getRegistrationStatus().equals(UserRegistrationStatus.NOT_CONFIRMED)) {
            log.error("Email not confirmed for user {}", user.getId());
            throw new InternalViolationException(EMAIL_NOT_CONFIRM_EXCEPTION);
        }
        if (user.getBannedStatus() == UserBannedStatus.BANNED) {
            throw new InternalViolationException(USER_BANNED);
        }
        if (user.getIsDeleted()) {
            throw new InternalViolationException(USER_BANNED);
        }
    }

}

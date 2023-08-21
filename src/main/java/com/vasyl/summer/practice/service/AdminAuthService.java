package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.configs.security.JwtUserDetailsService;
import com.vasyl.summer.practice.configs.security.jwt.JwtTokenProvider;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.models.AuthDetailsDto;
import com.vasyl.summer.practice.models.LoginAdminDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.vasyl.summer.practice.exceptions.InternalViolationType.INVALID_PASSWORD_EXCEPTION;
import static com.vasyl.summer.practice.exceptions.InternalViolationType.NOT_ENOUGH_RIGHTS;


@Slf4j
@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService jwtUserDetailsService;

    public AuthDetailsDto auth(LoginAdminDto dto) {
        User user = userService.getUserByEmail(dto.getEmail().toLowerCase());

        validAdminUser(user);

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

        log.debug("Admin: {} success logged in", user.getEmail());

        return new AuthDetailsDto(user.getId(), token, user.getRole());
    }

    @InvocationLog
    private void validAdminUser(User user) {
        if (user.getRole() != UserRole.ADMIN && user.getRole() != UserRole.SUPER_ADMIN) {
            log.error("User {} has not enough rights", user.getId());
            throw new InternalViolationException(NOT_ENOUGH_RIGHTS);
        }
    }

}

package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.annotation.InvocationLog;
import com.vasyl.summer.practice.configs.security.JwtUserDetailsService;
import com.vasyl.summer.practice.configs.security.jwt.JwtTokenProvider;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.enums.UserBannedStatus;
import com.vasyl.summer.practice.database.enums.UserRegistrationStatus;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.models.RegistrationUserDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.vasyl.summer.practice.database.enums.UserRegistrationStatus.CONFIRMED;
import static com.vasyl.summer.practice.database.enums.UserRegistrationStatus.NOT_CONFIRMED;
import static com.vasyl.summer.practice.exceptions.InternalViolationType.EMAIL_IN_USE_EXCEPTION;
import static com.vasyl.summer.practice.exceptions.InternalViolationType.EMAIL_NOT_CONFIRM_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final SpringMailSender mailGunEmailSender;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthService authService;


    @InvocationLog
    public void register(RegistrationUserDto registrationUserDto) {

        validSignUpRequest(registrationUserDto.getEmail());


        User user = createUser(registrationUserDto);
        user.setRegistrationStatus(NOT_CONFIRMED);
        userRepository.save(user);

        log.debug("User with email: {}, was success registered", user.getEmail());

        String token = jwtTokenProvider.createToken(user.getId());
        jwtUserDetailsService.saveUserToken(user, token);
    }


    @PostConstruct
    public void registerAdmin() {
        String adminEmail = "admin@flexychain.com";
        String adminPartnerId = "admin";
        String adminPassword = "Qaz12345";

        if (userRepository.existsByEmail(adminEmail)) {
            return;
        }

        try {
            RegistrationUserDto registrationUserDto = new RegistrationUserDto();
            registrationUserDto.setEmail(adminEmail);
            registrationUserDto.setPassword(adminPassword);

            User user = createUser(registrationUserDto);
            user.setPartnerId(adminPartnerId);
            user.setRegistrationStatus(CONFIRMED);
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);

            log.debug("Admin with email: {}, was success registered", user.getEmail());

            //save first user token
            String token = jwtTokenProvider.createToken(user.getId());
            jwtUserDetailsService.saveUserToken(user, token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @InvocationLog
    private User createUser(RegistrationUserDto dto) {
        var user = new User();

        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.USER);
        user.setBannedStatus(UserBannedStatus.NOT_BANNED);
        user.setRegistrationStatus(CONFIRMED);

        return user;
    }


    @InvocationLog
    private void validSignUpRequest(String email) {
        userRepository.getByEmail(email).ifPresent(user -> {
            if (user.getRegistrationStatus().equals(UserRegistrationStatus.NOT_CONFIRMED)) {
                log.error("User with email {} not confirmed email", email);
                throw new InternalViolationException(EMAIL_NOT_CONFIRM_EXCEPTION);
            }
            log.error("User with email {} already exists", email);
            throw new InternalViolationException(EMAIL_IN_USE_EXCEPTION);
        });
    }


}

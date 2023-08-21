package com.vasyl.summer.practice.configs.security;

import com.vasyl.summer.practice.configs.security.jwt.JwtUserFactory;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.redis.RedisAuthToken;
import com.vasyl.summer.practice.redis.repository.RedisAuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RedisAuthTokenRepository redisAuthTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String jwtId) {
        User user = userRepository.findById(jwtId).orElse(null);
        return JwtUserFactory.create(user);
    }

    public void saveUserToken(User user, String token) {
        RedisAuthToken authToken = redisAuthTokenRepository.findById(user.getId())
                .orElseGet(() -> {
                    RedisAuthToken redisAuthToken = new RedisAuthToken();
                    redisAuthToken.setUserId(user.getId());
                    return redisAuthToken;
                });
        authToken.setToken(token);
        redisAuthTokenRepository.save(authToken);
    }

    public String getUserToken(String userId) {
        RedisAuthToken authToken = redisAuthTokenRepository.findById(userId).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.USER_NOT_FOUND_EXCEPTION)
        );
        return authToken.getToken();
    }

    public RedisAuthToken getUserAuthTokenOrCreateNew(User user, String token) {
        return redisAuthTokenRepository.findById(user.getId()).orElseGet(
                () -> {
                    RedisAuthToken redisAuthToken = new RedisAuthToken();
                    redisAuthToken.setToken(token);
                    redisAuthToken.setUserId(user.getId());
                    return redisAuthTokenRepository.save(redisAuthToken);
                }
        );
    }

}

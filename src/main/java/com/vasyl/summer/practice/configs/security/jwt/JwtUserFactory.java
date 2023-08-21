package com.vasyl.summer.practice.configs.security.jwt;

import com.vasyl.summer.practice.database.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return JwtUser.builder()
                .id(user.getId())
                .accountNonLocked(true)
                .password(user.getPassword())
                .authorities(user.getAuthority(user.getRole()))
                .build();
    }

}

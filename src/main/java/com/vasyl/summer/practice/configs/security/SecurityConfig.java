package com.vasyl.summer.practice.configs.security;

import com.vasyl.summer.practice.configs.security.jwt.JwtConfigurer;
import com.vasyl.summer.practice.configs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((req) -> {
                            try {
                                req
                                        .and()
                                        .csrf().disable()
                                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                                .requestMatchers("/auth/**").permitAll()
                                                .requestMatchers("/admin/auth/**").permitAll()
                                                .requestMatchers("/api/service/health").permitAll()
                                                .requestMatchers("/api/service/prometheus").permitAll()
                                                .requestMatchers("/util/**").permitAll()
                                                .anyRequest().authenticated()
                                        )
                                        .apply(new JwtConfigurer(jwtTokenProvider));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
}

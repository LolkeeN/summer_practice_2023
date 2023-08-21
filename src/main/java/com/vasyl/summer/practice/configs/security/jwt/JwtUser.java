package com.vasyl.summer.practice.configs.security.jwt;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
public class JwtUser implements UserDetails {

    private final String id;
    private final String password;
    private final String salt;
    private final boolean accountNonLocked;
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
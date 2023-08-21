package com.vasyl.summer.practice.database.entity;

import com.vasyl.summer.practice.database.enums.UserBannedStatus;
import com.vasyl.summer.practice.database.enums.UserRegistrationStatus;
import com.vasyl.summer.practice.database.enums.UserRole;
import com.vasyl.summer.practice.util.RandomHelper;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private Long created;

    @Column(name = "partner_id", nullable = false)
    private String partnerId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "registration_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus registrationStatus;

    @Column(name = "banned_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserBannedStatus bannedStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
    @Column(name = "account_id", unique = true)
    private Long accountId;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.created = System.currentTimeMillis();
        this.accountId = RandomHelper.getRandomWebId();
    }

    public Set<GrantedAuthority> getAuthority(UserRole... roles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRole role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return grantedAuthorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", created=" + created +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationStatus=" + registrationStatus +
                ", bannedStatus=" + bannedStatus +
                ", role=" + role +
                ", partnerId=" + partnerId +
                '}';
    }

}

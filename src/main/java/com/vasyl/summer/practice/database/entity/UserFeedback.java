package com.vasyl.summer.practice.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserFeedback {

    @Id
    private String id;
    private Long created;
    private String text;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserFeedback() {
        this.id = UUID.randomUUID().toString();
        this.created = System.currentTimeMillis();
    }
}

package com.vasyl.summer.practice.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserQuestion {

    @Id
    private String id;
    private String text;
    private boolean isAnswered;
    private Long created;
    private String answer;
    @OneToOne
    private User user;

    public UserQuestion() {
        this.id = UUID.randomUUID().toString();
        this.created = System.currentTimeMillis();
    }
}

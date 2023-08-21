package com.vasyl.summer.practice.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity {

    @Id
    private String id;

    private Date date;
    private int startTime;
    private int endTime;
    private Long created;
    @ManyToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> users = new ArrayList<>();

    public Activity() {
        this.id = UUID.randomUUID().toString();
        this.created=System.currentTimeMillis();
    }
}

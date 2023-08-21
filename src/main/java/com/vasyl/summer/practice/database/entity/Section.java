package com.vasyl.summer.practice.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Section {

    @Id
    private String id;
    private String name;
    private String description;
    private int room;
    private Long created;
    @ManyToOne
    @JoinColumn(name = "complex_id", referencedColumnName = "id")
    private SportsComplex sportsComplex;
    @OneToMany
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private List<Activity> activities = new ArrayList<>();

    public Section() {
        this.id = UUID.randomUUID().toString();
        this.created=System.currentTimeMillis();
    }
}

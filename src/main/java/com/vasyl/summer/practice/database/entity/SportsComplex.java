package com.vasyl.summer.practice.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SportsComplex {

    @Id
    private String id;

    private String name;
    private String country;
    private String city;
    private String address;
    private String description;
    private Long created;
    @OneToMany(mappedBy = "sportsComplex")
    private List<Section> sections = new ArrayList<>();

    public SportsComplex() {
        this.id = UUID.randomUUID().toString();
        this.created = System.currentTimeMillis();
    }
}

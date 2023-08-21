package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.entity.Activity;
import com.vasyl.summer.practice.database.entity.SportsComplex;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionResponseDto {

    private String id;
    private String name;
    private String description;
    private int room;
    private Long created;
    private List<ActivityResponseDto> activities;
}

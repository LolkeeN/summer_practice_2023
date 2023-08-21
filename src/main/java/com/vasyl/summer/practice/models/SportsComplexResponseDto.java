package com.vasyl.summer.practice.models;

import com.vasyl.summer.practice.database.entity.Section;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SportsComplexResponseDto {
    @Id
    private String id;

    private String name;
    private String country;
    private String city;
    private String address;
    private String description;
    private Long created;
    private List<SectionResponseDto> sections;
}

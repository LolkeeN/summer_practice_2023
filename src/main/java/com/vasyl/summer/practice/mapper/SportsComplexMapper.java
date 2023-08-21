package com.vasyl.summer.practice.mapper;

import com.vasyl.summer.practice.database.entity.SportsComplex;
import com.vasyl.summer.practice.models.SportsComplexDto;
import com.vasyl.summer.practice.models.SportsComplexResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportsComplexMapper {

    private final SectionMapper sectionMapper;

    public void mapSportsComplexData(SportsComplexDto dto, SportsComplex sportsComplex){
        sportsComplex.setAddress(dto.getAddress());
        sportsComplex.setCity(dto.getCity());
        sportsComplex.setCountry(dto.getCountry());
        sportsComplex.setName(dto.getName());
        sportsComplex.setDescription(dto.getDescription());
    }

    public SportsComplexResponseDto toSportsComplexResponseDto(SportsComplex sportsComplex){
        SportsComplexResponseDto dto = new SportsComplexResponseDto();
        dto.setAddress(sportsComplex.getAddress());
        dto.setId(sportsComplex.getId());
        dto.setCreated(sportsComplex.getCreated());
        dto.setCity(sportsComplex.getCity());
        dto.setName(sportsComplex.getName());
        dto.setCountry(sportsComplex.getCountry());
        dto.setDescription(sportsComplex.getDescription());
        dto.setSections(sportsComplex.getSections().stream()
                .map(sectionMapper::toSectionResponseDto)
                .toList());

        return dto;
    }
}

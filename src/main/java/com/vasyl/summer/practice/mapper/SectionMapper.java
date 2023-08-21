package com.vasyl.summer.practice.mapper;

import com.vasyl.summer.practice.database.entity.Section;
import com.vasyl.summer.practice.database.repository.SportsComplexRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.models.SectionDto;
import com.vasyl.summer.practice.models.SectionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectionMapper {

    private final SportsComplexRepository sportsComplexRepository;
    private final ActivityMapper activityMapper;

    public void mapSectionData(SectionDto dto, Section section){
        section.setRoom(dto.getRoom());
        section.setName(dto.getName());
        section.setDescription(dto.getDescription());
        section.setSportsComplex(sportsComplexRepository.findById(dto.getSportsComplexId()).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.SPORTS_COMPLEX_NOT_FOUND)
        ));
    }

    public SectionResponseDto toSectionResponseDto(Section section){
        SectionResponseDto dto = new SectionResponseDto();
        dto.setId(section.getId());
        dto.setCreated(section.getCreated());
        dto.setRoom(section.getRoom());
        dto.setName(section.getName());
        dto.setDescription(section.getDescription());
        dto.setActivities(section.getActivities().stream()
                .map(activityMapper::toActivityResponseDto)
                .toList());

        return dto;
    }
}

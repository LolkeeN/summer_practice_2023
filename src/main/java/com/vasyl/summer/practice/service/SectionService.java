package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.database.entity.Activity;
import com.vasyl.summer.practice.database.entity.Section;
import com.vasyl.summer.practice.database.repository.SectionRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.mapper.SectionMapper;
import com.vasyl.summer.practice.models.ActivityDto;
import com.vasyl.summer.practice.models.ActivityResponseDto;
import com.vasyl.summer.practice.models.SectionDto;
import com.vasyl.summer.practice.models.SectionResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    public List<SectionResponseDto> getAllSections(){
        List<Section> sections = (List<Section>) sectionRepository.findAll();
        return sections.stream()
                .map(sectionMapper::toSectionResponseDto)
                .toList();
    }

    public SectionResponseDto getSectionResponseDtoByActivityId(String activityId){
        Section section = sectionRepository.findById(activityId).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.SECTION_NOT_FOUND)
        );
        return sectionMapper.toSectionResponseDto(section);
    }

    public void updateSection(SectionDto sectionDto, String id){
        Section section = sectionRepository.findById(id).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.SECTION_NOT_FOUND)
        );
        sectionMapper.mapSectionData(sectionDto, section);
        sectionRepository.save(section);
    }

    public void createSection(SectionDto dto){
        Section section = new Section();
        sectionMapper.mapSectionData(dto, section);
        sectionRepository.save(section);
    }

    public void deleteSectionById(String id){
        sectionRepository.deleteById(id);
    }

    public Section getSectionById(String sectionId){
        return sectionRepository.findById(sectionId).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.SECTION_NOT_FOUND)
        );
    }

    public void save(Section section){
        sectionRepository.save(section);
    }
}

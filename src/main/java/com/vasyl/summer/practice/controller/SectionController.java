package com.vasyl.summer.practice.controller;

import com.vasyl.summer.practice.models.SectionDto;
import com.vasyl.summer.practice.models.SectionResponseDto;
import com.vasyl.summer.practice.service.SectionService;
import com.vasyl.summer.practice.service.SectionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping
    public List<SectionResponseDto> getAllSections(){
        return sectionService.getAllSections();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDto> getSectionById(@PathVariable String id){
        SectionResponseDto sectionResponseDtoByActivityId = sectionService.getSectionResponseDtoByActivityId(id);
        return ResponseEntity.ok(sectionResponseDtoByActivityId);
    }
    
    @PostMapping
    public void createSection(@RequestBody SectionDto sectionDto){
        sectionService.createSection(sectionDto);
    }
    
    @PutMapping("/{id}")
    public void updateSectionById(@PathVariable String id, @RequestBody SectionDto dto){
        sectionService.updateSection(dto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteSectionById(@PathVariable String id){
        sectionService.deleteSectionById(id);
    }
}

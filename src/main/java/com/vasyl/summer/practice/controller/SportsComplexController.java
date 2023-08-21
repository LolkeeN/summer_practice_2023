package com.vasyl.summer.practice.controller;

import com.vasyl.summer.practice.database.entity.SportsComplex;
import com.vasyl.summer.practice.models.SportsComplexDto;
import com.vasyl.summer.practice.models.SportsComplexResponseDto;
import com.vasyl.summer.practice.service.SportsComplexService;
import java.util.List;
import lombok.Getter;
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
@RequestMapping("/sports-complex")
@RequiredArgsConstructor
public class SportsComplexController {

    private final SportsComplexService sportsComplexService;

    @GetMapping
    public List<SportsComplexResponseDto> getAllSportsComplex(){
        return sportsComplexService.getAllSportsComplexes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportsComplexResponseDto> getSportComplexById(@PathVariable String id){
        SportsComplexResponseDto dto = sportsComplexService.getSportsComplexResponseDtoById(id);

        return ResponseEntity.ok(dto);
    }
    
    @PostMapping
    public void createSportsComplex(@RequestBody SportsComplexDto sportsComplexDto){
        sportsComplexService.createSportsComplex(sportsComplexDto);
    }
    
    @PutMapping("/{id}")
    public void updateSportsComplexById(@PathVariable String id, @RequestBody SportsComplexDto dto){
        sportsComplexService.updateSportComplexById(id, dto);
    }
    @DeleteMapping("/{id}")
    public void deleteSportsComplexById(@PathVariable String id){
        sportsComplexService.deleteSportsComplexById(id);
    }
}

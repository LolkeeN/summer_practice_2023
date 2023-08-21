package com.vasyl.summer.practice.service;

import com.vasyl.summer.practice.database.entity.SportsComplex;
import com.vasyl.summer.practice.database.repository.SportsComplexRepository;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.mapper.SportsComplexMapper;
import com.vasyl.summer.practice.models.SportsComplexDto;
import com.vasyl.summer.practice.models.SportsComplexResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportsComplexService {

    private final SportsComplexRepository sportsComplexRepository;
    private final SportsComplexMapper sportsComplexMapper;
    public List<SportsComplexResponseDto> getAllSportsComplexes(){
        List<SportsComplex> sportsComplexes = (List<SportsComplex>) sportsComplexRepository.findAll();

        return sportsComplexes.stream()
                .map(sportsComplexMapper::toSportsComplexResponseDto)
                .toList();
    }

    public SportsComplexResponseDto getSportsComplexResponseDtoById(String sportComplexId){
        SportsComplex SportsComplex = sportsComplexRepository.findById(sportComplexId).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.SPORTS_COMPLEX_NOT_FOUND)
        );
        return sportsComplexMapper.toSportsComplexResponseDto(SportsComplex);
    }

    public void updateSportComplexById(String id, SportsComplexDto sportsComplexDto){
        SportsComplex sportsComplex = sportsComplexRepository.findById(id).orElseThrow(
                () -> new InternalViolationException(InternalViolationType.SPORTS_COMPLEX_NOT_FOUND)
        );
        sportsComplexMapper.mapSportsComplexData(sportsComplexDto, sportsComplex);
        sportsComplexRepository.save(sportsComplex);
    }

    public void createSportsComplex(SportsComplexDto dto){
        SportsComplex sportsComplex = new SportsComplex();
        sportsComplexMapper.mapSportsComplexData(dto, sportsComplex);
        sportsComplexRepository.save(sportsComplex);
    }

    public void deleteSportsComplexById(String id){
        sportsComplexRepository.deleteById(id);
    }
}

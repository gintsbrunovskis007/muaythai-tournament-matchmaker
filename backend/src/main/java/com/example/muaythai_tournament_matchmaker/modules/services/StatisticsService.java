package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import com.example.muaythai_tournament_matchmaker.models.fighter.Fighter;
import com.example.muaythai_tournament_matchmaker.models.tournament.Statistics;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.StatisticsDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.FightRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.FighterRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private FighterRepository fighterRepository;

    @Autowired
    private FightRepository fightRepository;

    public List<StatisticsDTO> getAllStatistics() {
        return statisticsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StatisticsDTO getStatisticsById(Long id) {
        Statistics statistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistics", id));
        return convertToDTO(statistics);
    }

    public List<StatisticsDTO> getStatisticsByFighter(Long fighterId) {
        return statisticsRepository.findByFighterId(fighterId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<StatisticsDTO> getStatisticsByFight(Long fightId) {
        return statisticsRepository.findByFightId(fightId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StatisticsDTO getStatisticsByFightAndFighter(Long fightId, Long fighterId) {
        Statistics statistics = statisticsRepository.findByFightIdAndFighterId(fightId, fighterId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Statistics for fight " + fightId + " and fighter " + fighterId));
        return convertToDTO(statistics);
    }

    public StatisticsDTO createStatistics(StatisticsDTO statisticsDTO) {
        Statistics statistics = convertToEntity(statisticsDTO);
        Statistics savedStatistics = statisticsRepository.save(statistics);
        return convertToDTO(savedStatistics);
    }

    public StatisticsDTO updateStatistics(Long id, StatisticsDTO statisticsDTO) {
        Statistics existingStatistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistics", id));

        updateEntityFromDTO(existingStatistics, statisticsDTO);
        Statistics updatedStatistics = statisticsRepository.save(existingStatistics);
        return convertToDTO(updatedStatistics);
    }

    public void deleteStatistics(Long id) {
        if (!statisticsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Statistics", id);
        }
        statisticsRepository.deleteById(id);
    }

    private StatisticsDTO convertToDTO(Statistics statistics) {
        StatisticsDTO dto = new StatisticsDTO();

        if (statistics.getFighter() != null) {
            dto.setFighterId(statistics.getFighter().getId());
        }
        if (statistics.getFight() != null) {
            dto.setFightId(statistics.getFight().getId());
        }

        dto.setPunchesAttempted(statistics.getPunchesAttempted());
        dto.setPunchesLanded(statistics.getPunchesLanded());
        dto.setKicksAttempted(statistics.getKicksAttempted());
        dto.setKicksLanded(statistics.getKicksLanded());
        dto.setElbowAttempted(statistics.getElbowAttempted());
        dto.setElbowLanded(statistics.getElbowLanded());
        dto.setKneeAttempted(statistics.getKneeAttempted());
        dto.setKneeLanded(statistics.getKneeLanded());
        dto.setHeadStrikesAttempted(statistics.getHeadStrikesAttempted());
        dto.setHeadStrikesLanded(statistics.getHeadStrikesLanded());
        dto.setBodyStrikesAttempted(statistics.getBodyStrikesAttempted());
        dto.setBodyStrikesLanded(statistics.getBodyStrikesLanded());
        dto.setLegStrikesAttempted(statistics.getLegStrikesAttempted());
        dto.setLegStrikesLanded(statistics.getLegStrikesLanded());
        dto.setKnockDowns(statistics.getKnockDowns());
        dto.setKnockOuts(statistics.getKnockOuts());
        dto.setTechnicalKnockOuts(statistics.getTechnicalKnockOuts());
        dto.setClinchControlTime(statistics.getClinchControlTime());
        dto.setFouls(statistics.getFouls());

        return dto;
    }

    private Statistics convertToEntity(StatisticsDTO dto) {
        Statistics statistics = new Statistics();

        if (dto.getFighterId() != null) {
            Fighter fighter = fighterRepository.findById(dto.getFighterId())
                    .orElseThrow(() -> new ResourceNotFoundException("Fighter", dto.getFighterId()));
            statistics.setFighter(fighter);
        }
        if (dto.getFightId() != null) {
            Fight fight = fightRepository.findById(dto.getFightId())
                    .orElseThrow(() -> new ResourceNotFoundException("Fight", dto.getFightId()));
            statistics.setFight(fight);
        }

        statistics.setPunchesAttempted(dto.getPunchesAttempted());
        statistics.setPunchesLanded(dto.getPunchesLanded());
        statistics.setKicksAttempted(dto.getKicksAttempted());
        statistics.setKicksLanded(dto.getKicksLanded());
        statistics.setElbowAttempted(dto.getElbowAttempted());
        statistics.setElbowLanded(dto.getElbowLanded());
        statistics.setKneeAttempted(dto.getKneeAttempted());
        statistics.setKneeLanded(dto.getKneeLanded());
        statistics.setHeadStrikesAttempted(dto.getHeadStrikesAttempted());
        statistics.setHeadStrikesLanded(dto.getHeadStrikesLanded());
        statistics.setBodyStrikesAttempted(dto.getBodyStrikesAttempted());
        statistics.setBodyStrikesLanded(dto.getBodyStrikesLanded());
        statistics.setLegStrikesAttempted(dto.getLegStrikesAttempted());
        statistics.setLegStrikesLanded(dto.getLegStrikesLanded());
        statistics.setKnockDowns(dto.getKnockDowns());
        statistics.setKnockOuts(dto.getKnockOuts());
        statistics.setTechnicalKnockOuts(dto.getTechnicalKnockOuts());
        statistics.setClinchControlTime(dto.getClinchControlTime());
        statistics.setFouls(dto.getFouls());

        return statistics;
    }

    private void updateEntityFromDTO(Statistics statistics, StatisticsDTO dto) {
        statistics.setPunchesAttempted(dto.getPunchesAttempted());
        statistics.setPunchesLanded(dto.getPunchesLanded());
        statistics.setKicksAttempted(dto.getKicksAttempted());
        statistics.setKicksLanded(dto.getKicksLanded());
        statistics.setElbowAttempted(dto.getElbowAttempted());
        statistics.setElbowLanded(dto.getElbowLanded());
        statistics.setKneeAttempted(dto.getKneeAttempted());
        statistics.setKneeLanded(dto.getKneeLanded());
        statistics.setHeadStrikesAttempted(dto.getHeadStrikesAttempted());
        statistics.setHeadStrikesLanded(dto.getHeadStrikesLanded());
        statistics.setBodyStrikesAttempted(dto.getBodyStrikesAttempted());
        statistics.setBodyStrikesLanded(dto.getBodyStrikesLanded());
        statistics.setLegStrikesAttempted(dto.getLegStrikesAttempted());
        statistics.setLegStrikesLanded(dto.getLegStrikesLanded());
        statistics.setKnockDowns(dto.getKnockDowns());
        statistics.setKnockOuts(dto.getKnockOuts());
        statistics.setTechnicalKnockOuts(dto.getTechnicalKnockOuts());
        statistics.setClinchControlTime(dto.getClinchControlTime());
        statistics.setFouls(dto.getFouls());
    }
}
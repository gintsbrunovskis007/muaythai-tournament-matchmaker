package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.StatisticsDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<List<StatisticsDTO>> getAllStatistics() {
        List<StatisticsDTO> statistics = statisticsService.getAllStatistics();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatisticsDTO> getStatisticsById(@PathVariable Long id) {
        StatisticsDTO statistics = statisticsService.getStatisticsById(id);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/fighter/{fighterId}")
    public ResponseEntity<List<StatisticsDTO>> getStatisticsByFighter(@PathVariable Long fighterId) {
        List<StatisticsDTO> statistics = statisticsService.getStatisticsByFighter(fighterId);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/fight/{fightId}")
    public ResponseEntity<List<StatisticsDTO>> getStatisticsByFight(@PathVariable Long fightId) {
        List<StatisticsDTO> statistics = statisticsService.getStatisticsByFight(fightId);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/fight/{fightId}/fighter/{fighterId}")
    public ResponseEntity<StatisticsDTO> getStatisticsByFightAndFighter(
            @PathVariable Long fightId,
            @PathVariable Long fighterId) {
        StatisticsDTO statistics = statisticsService.getStatisticsByFightAndFighter(fightId, fighterId);
        return ResponseEntity.ok(statistics);
    }

    @PostMapping
    public ResponseEntity<StatisticsDTO> createStatistics(@RequestBody StatisticsDTO statisticsDTO) {
        StatisticsDTO createdStatistics = statisticsService.createStatistics(statisticsDTO);
        return ResponseEntity.ok(createdStatistics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatisticsDTO> updateStatistics(
            @PathVariable Long id,
            @RequestBody StatisticsDTO statisticsDTO) {
        StatisticsDTO updatedStatistics = statisticsService.updateStatistics(id, statisticsDTO);
        return ResponseEntity.ok(updatedStatistics);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistics(@PathVariable Long id) {
        statisticsService.deleteStatistics(id);
        return ResponseEntity.noContent().build();
    }
}
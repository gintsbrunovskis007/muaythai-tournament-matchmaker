package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.RoundScoreDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.RoundScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roundscores")
public class RoundScoreController {

    @Autowired
    private RoundScoreService roundScoreService;

    @GetMapping("/scorecard/{scorecardId}")
    public ResponseEntity<List<RoundScoreDTO>> getRoundScoresByScorecard(@PathVariable Long scorecardId) {
        List<RoundScoreDTO> roundScores = roundScoreService.getRoundScoresByScorecard(scorecardId);
        return ResponseEntity.ok(roundScores);
    }

    @GetMapping("/fight/{fightId}")
    public ResponseEntity<List<RoundScoreDTO>> getRoundScoresByFight(@PathVariable Integer fightId) {
        List<RoundScoreDTO> roundScores = roundScoreService.getRoundScoresByFight(fightId);
        return ResponseEntity.ok(roundScores);
    }

    @GetMapping("/fight/{fightId}/round/{roundNumber}")
    public ResponseEntity<List<RoundScoreDTO>> getRoundScoresByFightAndRound(
            @PathVariable Integer fightId,
            @PathVariable Integer roundNumber) {
        List<RoundScoreDTO> roundScores = roundScoreService.getRoundScoresByFightAndRound(fightId, roundNumber);
        return ResponseEntity.ok(roundScores);
    }

    @PostMapping
    public ResponseEntity<RoundScoreDTO> createRoundScore(@RequestBody RoundScoreDTO roundScoreDTO) {
        RoundScoreDTO createdRoundScore = roundScoreService.createRoundScore(roundScoreDTO);
        return ResponseEntity.ok(createdRoundScore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoundScoreDTO> updateRoundScore(
            @PathVariable Long id,
            @RequestBody RoundScoreDTO roundScoreDTO) {
        RoundScoreDTO updatedRoundScore = roundScoreService.updateRoundScore(id, roundScoreDTO);
        return ResponseEntity.ok(updatedRoundScore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoundScore(@PathVariable Long id) {
        roundScoreService.deleteRoundScore(id);
        return ResponseEntity.noContent().build();
    }
}
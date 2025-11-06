package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.RoundScoreDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.ScoreCardDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scorecards")
public class ScoreCardController {

    @Autowired
    private ScoreCardService scorecardService;

    @PostMapping
    public ResponseEntity<ScoreCardDTO> createScorecard(@RequestBody ScoreCardDTO scorecardDTO) {
        ScoreCardDTO createdScorecard = scorecardService.createScorecard(scorecardDTO);
        return ResponseEntity.ok(createdScorecard);
    }

    @GetMapping("/fight/{fightId}")
    public ResponseEntity<List<ScoreCardDTO>> getScorecardsByFight(@PathVariable Long fightId) {
        List<ScoreCardDTO> scorecards = scorecardService.getScorecardsByFight(fightId);
        return ResponseEntity.ok(scorecards);
    }

    @PostMapping("/{scorecardId}/rounds")
    public ResponseEntity<ScoreCardDTO> submitRoundScores(
            @PathVariable Long scorecardId,
            @RequestBody List<RoundScoreDTO> roundScores) {
        ScoreCardDTO updatedScorecard = scorecardService.submitRoundScores(scorecardId, roundScores);
        return ResponseEntity.ok(updatedScorecard);
    }

    @PostMapping("/fight/{fightId}/determine-winner")
    public ResponseEntity<Void> determineFightWinner(@PathVariable Long fightId) {
        scorecardService.determineFightWinner(fightId);
        return ResponseEntity.ok().build();
    }
}
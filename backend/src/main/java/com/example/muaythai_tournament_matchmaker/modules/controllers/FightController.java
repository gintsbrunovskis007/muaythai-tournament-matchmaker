package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.models.fight.Method;
import com.example.muaythai_tournament_matchmaker.models.fight.Result;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.FightDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fights")
public class FightController {

    @Autowired
    private FightService fightService;

    @GetMapping
    public ResponseEntity<List<FightDTO>> getAllFights() {
        List<FightDTO> fights = fightService.getAllFights();
        return ResponseEntity.ok(fights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FightDTO> getFightById(@PathVariable Long id) {
        FightDTO fight = fightService.getFightById(id);
        return ResponseEntity.ok(fight);
    }

    @PostMapping
    public ResponseEntity<FightDTO> createFight(@RequestBody FightDTO fightDTO) {
        FightDTO createdFight = fightService.createFight(fightDTO);
        return ResponseEntity.ok(createdFight);
    }

    @PutMapping("/{id}/result")
    public ResponseEntity<FightDTO> updateFightResult(
            @PathVariable Long id,
            @RequestParam Result result,
            @RequestParam Method method,
            @RequestParam(required = false) Long winnerId) {
        FightDTO updatedFight = fightService.updateFightResult(id, result, method, winnerId);
        return ResponseEntity.ok(updatedFight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFight(@PathVariable Long id) {
        fightService.deleteFight(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.FighterDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.FighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fighters")
public class FighterController {

    @Autowired
    private FighterService fighterService;

    @GetMapping
    public ResponseEntity<List<FighterDTO>> getAllFighters() {
        List<FighterDTO> fighters = fighterService.getAllFighters();
        return ResponseEntity.ok(fighters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FighterDTO> getFighterById(@PathVariable Long id) {
        FighterDTO fighter = fighterService.getFighterById(id);
        return ResponseEntity.ok(fighter);
    }

    @PostMapping
    public ResponseEntity<FighterDTO> createFighter(@RequestBody FighterDTO fighterDTO) {
        FighterDTO createdFighter = fighterService.createFighter(fighterDTO);
        return ResponseEntity.ok(createdFighter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FighterDTO> updateFighter(@PathVariable Long id, @RequestBody FighterDTO fighterDTO) {
        FighterDTO updatedFighter = fighterService.updateFighter(id, fighterDTO);
        return ResponseEntity.ok(updatedFighter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFighter(@PathVariable Long id) {
        fighterService.deleteFighter(id);
        return ResponseEntity.noContent().build();
    }
}
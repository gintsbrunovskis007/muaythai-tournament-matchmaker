package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.CoachDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping
    public ResponseEntity<List<CoachDTO>> getAllCoaches() {
        List<CoachDTO> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachDTO> getCoachById(@PathVariable Long id) {
        CoachDTO coach = coachService.getCoachById(id);
        return ResponseEntity.ok(coach);
    }

    @GetMapping("/gym/{gymId}")
    public ResponseEntity<List<CoachDTO>> getCoachesByGym(@PathVariable Long gymId) {
        List<CoachDTO> coaches = coachService.getCoachesByGym(gymId);
        return ResponseEntity.ok(coaches);
    }

    @PostMapping
    public ResponseEntity<CoachDTO> createCoach(@RequestBody CoachDTO coachDTO) {
        CoachDTO createdCoach = coachService.createCoach(coachDTO);
        return ResponseEntity.ok(createdCoach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoachDTO> updateCoach(@PathVariable Long id, @RequestBody CoachDTO coachDTO) {
        CoachDTO updatedCoach = coachService.updateCoach(id, coachDTO);
        return ResponseEntity.ok(updatedCoach);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}
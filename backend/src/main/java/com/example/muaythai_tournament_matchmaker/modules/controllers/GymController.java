package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.GymDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping
    public ResponseEntity<List<GymDTO>> getAllGyms() {
        List<GymDTO> gyms = gymService.getAllGyms();
        return ResponseEntity.ok(gyms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymDTO> getGymById(@PathVariable Long id) {
        GymDTO gym = gymService.getGymById(id);
        return ResponseEntity.ok(gym);
    }

    @PostMapping
    public ResponseEntity<GymDTO> createGym(@RequestBody GymDTO gymDTO) {
        GymDTO createdGym = gymService.createGym(gymDTO);
        return ResponseEntity.ok(createdGym);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long id) {
        gymService.deleteGym(id);
        return ResponseEntity.noContent().build();
    }
}
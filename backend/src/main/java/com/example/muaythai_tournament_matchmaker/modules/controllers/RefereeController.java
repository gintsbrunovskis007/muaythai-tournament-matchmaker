package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.RefereeDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/referees")
public class RefereeController {

    @Autowired
    private RefereeService refereeService;

    @GetMapping
    public ResponseEntity<List<RefereeDTO>> getAllReferees() {
        List<RefereeDTO> referees = refereeService.getAllReferees();
        return ResponseEntity.ok(referees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefereeDTO> getRefereeById(@PathVariable Long id) {
        RefereeDTO referee = refereeService.getRefereeById(id);
        return ResponseEntity.ok(referee);
    }

    @PostMapping
    public ResponseEntity<RefereeDTO> createReferee(@RequestBody RefereeDTO refereeDTO) {
        RefereeDTO createdReferee = refereeService.createReferee(refereeDTO);
        return ResponseEntity.ok(createdReferee);
    }
}
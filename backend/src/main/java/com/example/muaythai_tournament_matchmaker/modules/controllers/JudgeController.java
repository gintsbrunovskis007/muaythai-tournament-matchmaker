package com.example.muaythai_tournament_matchmaker.modules.controllers;

import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.JudgeDTO;
import com.example.muaythai_tournament_matchmaker.modules.services.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/judges")
public class JudgeController {

    @Autowired
        private JudgeService judgeService;

    @GetMapping
    public ResponseEntity<List<JudgeDTO>> getAllJudges() {
        List<JudgeDTO> judges = judgeService.getAllJudges();
        return ResponseEntity.ok(judges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudgeDTO> getJudgeById(@PathVariable Long id) {
        JudgeDTO judge = judgeService.getJudgeById(id);
        return ResponseEntity.ok(judge);
    }

    @PostMapping
    public ResponseEntity<JudgeDTO> createJudge(@RequestBody JudgeDTO judgeDTO) {
        JudgeDTO createdJudge = judgeService.createJudge(judgeDTO);
        return ResponseEntity.ok(createdJudge);
    }
}
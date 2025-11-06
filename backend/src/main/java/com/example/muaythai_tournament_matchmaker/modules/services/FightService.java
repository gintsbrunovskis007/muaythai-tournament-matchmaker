package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import com.example.muaythai_tournament_matchmaker.models.fight.Method;
import com.example.muaythai_tournament_matchmaker.models.fight.Result;
import com.example.muaythai_tournament_matchmaker.models.fighter.Fighter;
import com.example.muaythai_tournament_matchmaker.models.tournament.Event;
import com.example.muaythai_tournament_matchmaker.models.tournament.Judge;
import com.example.muaythai_tournament_matchmaker.models.tournament.Referee;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.FightDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FightService {

    @Autowired
    private FightRepository fightRepository;

    @Autowired
    private FighterRepository fighterRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RefereeRepository refereeRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    public List<FightDTO> getAllFights() {
        return fightRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FightDTO getFightById(Long id) {
        Fight fight = fightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fight", id));
        return convertToDTO(fight);
    }

    public FightDTO createFight(FightDTO fightDTO) {
        Fight fight = convertToEntity(fightDTO);
        Fight savedFight = fightRepository.save(fight);
        return convertToDTO(savedFight);
    }

    public void deleteFight(Long id) {
        if (!fightRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fight", id);
        }
        fightRepository.deleteById(id);
    }

    public FightDTO updateFightResult(Long fightId, Result result, Method method, Long winnerId) {
        Fight fight = fightRepository.findById(fightId)
                .orElseThrow(() -> new ResourceNotFoundException("Fight", fightId));

        fight.setResult(result);
        fight.setMethod(method);

        if (winnerId != null) {
            Fighter winner = fighterRepository.findById(winnerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Fighter", winnerId));
            fight.setWinner(winner);
        }

        Fight updatedFight = fightRepository.save(fight);
        return convertToDTO(updatedFight);
    }

    private FightDTO convertToDTO(Fight fight) {
        FightDTO dto = new FightDTO();
        dto.setEventID(fight.getEvent().getId());
        dto.setRedCornerFighterID((long) fight.getRedCorner().getId().intValue());
        dto.setBlueCornerFighterID((long) fight.getBlueCorner().getId().intValue());

        if (fight.getReferee() != null) {
            dto.setRefereeID(fight.getReferee().getId());
        }

        if (fight.getWinner() != null) {
            dto.setWinnerID(fight.getWinner().getId());
        }

        dto.setResult(fight.getResult() != null ? fight.getResult().name() : null);
        dto.setMethod(fight.getMethod() != null ? fight.getMethod().name() : null);
        dto.setTotalRounds(fight.getTotalRounds());
        dto.setRoundFinished(fight.getRoundFinished());
        dto.setFinishTime(fight.getFinishTime());

        List<Judge> judges = dto.getJudgesIds() != null
                ? dto.getJudgesIds().stream()
                .map(id -> judgeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Judge", id)))
                .collect(Collectors.toList())
                : new ArrayList<>();
        fight.setJudges(judges);




        return dto;
    }

    private Fight convertToEntity(FightDTO dto) {
        Fight fight = new Fight();

        // Event
        if (dto.getEventID() == null) throw new IllegalArgumentException("Event ID is required");
        Event event = eventRepository.findById(dto.getEventID())
                .orElseThrow(() -> new ResourceNotFoundException("Event", dto.getEventID()));
        fight.setEvent(event);

        if (dto.getRedCornerFighterID() == null || dto.getBlueCornerFighterID() == null) {
            throw new IllegalArgumentException("Both red and blue corner fighters are required");
        }
        Fighter redCorner = fighterRepository.findById(dto.getRedCornerFighterID())
                .orElseThrow(() -> new ResourceNotFoundException("Fighter", dto.getRedCornerFighterID()));
        Fighter blueCorner = fighterRepository.findById(dto.getBlueCornerFighterID())
                .orElseThrow(() -> new ResourceNotFoundException("Fighter", dto.getBlueCornerFighterID()));
        fight.setRedCorner(redCorner);
        fight.setBlueCorner(blueCorner);

        if (dto.getRefereeID() != null) {
            Referee referee = refereeRepository.findById(dto.getRefereeID())
                    .orElseThrow(() -> new ResourceNotFoundException("Referee", dto.getRefereeID()));
            fight.setReferee(referee);
        }

        if (dto.getJudgesIds() == null || dto.getJudgesIds().size() != 3) {
            throw new IllegalArgumentException("Exactly 3 judges are required");
        }
        List<Judge> judges = dto.getJudgesIds() != null
                ? dto.getJudgesIds().stream()
                .map(id -> judgeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Judge", id)))
                .collect(Collectors.toList())
                : new ArrayList<>();
        fight.setJudges(judges);



        if (dto.getTotalRounds() == null || dto.getTotalRounds() < 1) {
            throw new IllegalArgumentException("Total rounds must be at least 1");
        }
        fight.setTotalRounds(dto.getTotalRounds());

        return fight;
    }


}
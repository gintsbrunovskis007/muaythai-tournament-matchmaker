package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fight.RoundScore;
import com.example.muaythai_tournament_matchmaker.models.fight.ScoreCard;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.RoundScoreDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.RoundScoreRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundScoreService {

    @Autowired
    private RoundScoreRepository roundScoreRepository;

    @Autowired
    private ScoreCardRepository scorecardRepository;

    public List<RoundScoreDTO> getRoundScoresByScorecard(Long scorecardId) {
        return roundScoreRepository.findByScorecardId(scorecardId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RoundScoreDTO> getRoundScoresByFight(Integer fightId) {
        return roundScoreRepository.findByScorecardFightId(fightId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RoundScoreDTO createRoundScore(RoundScoreDTO roundScoreDTO) {
        RoundScore roundScore = convertToEntity(roundScoreDTO);
        RoundScore savedRoundScore = roundScoreRepository.save(roundScore);

        updateScorecardTotals(savedRoundScore.getScorecard().getId());

        return convertToDTO(savedRoundScore);
    }

    public RoundScoreDTO updateRoundScore(Long roundScoreId, RoundScoreDTO roundScoreDTO) {
        RoundScore existingRoundScore = roundScoreRepository.findById(roundScoreId)
                .orElseThrow(() -> new ResourceNotFoundException("RoundScore", roundScoreId));

        updateEntityFromDTO(existingRoundScore, roundScoreDTO);
        RoundScore updatedRoundScore = roundScoreRepository.save(existingRoundScore);

        updateScorecardTotals(updatedRoundScore.getScorecard().getId());

        return convertToDTO(updatedRoundScore);
    }

    public void deleteRoundScore(Long roundScoreId) {
        RoundScore roundScore = roundScoreRepository.findById(roundScoreId)
                .orElseThrow(() -> new ResourceNotFoundException("RoundScore", roundScoreId));

        Long scorecardId = roundScore.getScorecard().getId();
        roundScoreRepository.deleteById(roundScoreId);

        updateScorecardTotals(scorecardId);
    }

    public List<RoundScoreDTO> getRoundScoresByFightAndRound(Integer fightId, Integer roundNumber) {
        List<RoundScore> roundScores = roundScoreRepository.findByScorecardFightId(fightId).stream()
                .filter(rs -> rs.getRoundNumber().equals(roundNumber))
                .collect(Collectors.toList());

        return roundScores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void updateScorecardTotals(Long scorecardId) {
        ScoreCard scorecard = scorecardRepository.findById(scorecardId)
                .orElseThrow(() -> new ResourceNotFoundException("Scorecard", scorecardId));

        scorecard.calculateTotalScores();
        scorecardRepository.save(scorecard);
    }

    private RoundScoreDTO convertToDTO(RoundScore roundScore) {
        RoundScoreDTO dto = new RoundScoreDTO();
        dto.setScoreCardID((long) roundScore.getScorecard().getId().intValue());
        dto.setRoundNumber(roundScore.getRoundNumber());
        dto.setFighterCorner(roundScore.getFighterCorner());
        dto.setScore(roundScore.getScore());
        return dto;
    }

    private RoundScore convertToEntity(RoundScoreDTO dto) {
        RoundScore roundScore = new RoundScore();

        ScoreCard scorecard = scorecardRepository.findById((long) dto.getScoreCardID())
                .orElseThrow(() -> new ResourceNotFoundException("Scorecard", dto.getScoreCardID()));
        roundScore.setScorecard(scorecard);

        roundScore.setRoundNumber(dto.getRoundNumber());
        roundScore.setFighterCorner(dto.getFighterCorner());
        roundScore.setScore(dto.getScore());

        return roundScore;
    }

    private void updateEntityFromDTO(RoundScore roundScore, RoundScoreDTO dto) {
        if (dto.getRoundNumber() != null) roundScore.setRoundNumber(dto.getRoundNumber());
        if (dto.getFighterCorner() != null) roundScore.setFighterCorner(dto.getFighterCorner());
        if (dto.getScore() != null) roundScore.setScore(dto.getScore());

        if (dto.getScoreCardID() != 0 && !roundScore.getScorecard().getId().equals((long) dto.getScoreCardID())) {
            ScoreCard scorecard = scorecardRepository.findById((long) dto.getScoreCardID())
                    .orElseThrow(() -> new ResourceNotFoundException("Scorecard", dto.getScoreCardID()));
            roundScore.setScorecard(scorecard);
        }
    }
}
package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import com.example.muaythai_tournament_matchmaker.models.fight.RoundScore;
import com.example.muaythai_tournament_matchmaker.models.fight.ScoreCard;
import com.example.muaythai_tournament_matchmaker.models.tournament.Judge;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.RoundScoreDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.ScoreCardDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.FightRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.JudgeRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreCardService {

    @Autowired
    private ScoreCardRepository scorecardRepository;

    @Autowired
    private FightRepository fightRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    public ScoreCardDTO createScorecard(ScoreCardDTO scorecardDTO) {
        ScoreCard scorecard = convertToEntity(scorecardDTO);
        ScoreCard savedScorecard = scorecardRepository.save(scorecard);

        savedScorecard.calculateTotalScores();
        scorecardRepository.save(savedScorecard);

        return convertToDTO(savedScorecard);
    }

    public List<ScoreCardDTO> getScorecardsByFight(Long fightId) {
        return scorecardRepository.findByFightId(fightId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ScoreCardDTO submitRoundScores(Long scorecardId, List<RoundScoreDTO> roundScores) {
        ScoreCard scorecard = scorecardRepository.findById(scorecardId)
                .orElseThrow(() -> new ResourceNotFoundException("Scorecard", scorecardId));

        scorecard.getRoundScores().clear();

        for (RoundScoreDTO roundScoreDTO : roundScores) {
            RoundScore roundScore = new RoundScore();
            roundScore.setScorecard(scorecard);
            roundScore.setRoundNumber(roundScoreDTO.getRoundNumber());
            roundScore.setFighterCorner(roundScoreDTO.getFighterCorner());
            roundScore.setScore(roundScoreDTO.getScore());
            scorecard.addRoundScore(roundScore);
        }

        scorecard.calculateTotalScores();
        ScoreCard updatedScorecard = scorecardRepository.save(scorecard);

        return convertToDTO(updatedScorecard);
    }

    public void determineFightWinner(Long fightId) {
        List<ScoreCard> scorecards = scorecardRepository.findByFightId(fightId);
        Fight fight = fightRepository.findById(fightId)
                .orElseThrow(() -> new ResourceNotFoundException("Fight", Long.valueOf(fightId)));

        fight.setScorecards(scorecards);
        fight.determineWinner();
        fightRepository.save(fight);
    }

    private ScoreCardDTO convertToDTO(ScoreCard scorecard) {
        ScoreCardDTO dto = new ScoreCardDTO();
        dto.setFightID(scorecard.getFight().getId());
        dto.setJudgeID(scorecard.getJudge().getId());
        dto.setComments(scorecard.getComments());

        List<RoundScoreDTO> roundScoreDTOs = scorecard.getRoundScores().stream()
                .map(this::convertRoundScoreToDTO)
                .collect(Collectors.toList());
        dto.setRoundScores(roundScoreDTOs);

        return dto;
    }

    private RoundScoreDTO convertRoundScoreToDTO(RoundScore roundScore) {
        RoundScoreDTO dto = new RoundScoreDTO();
        dto.setScoreCardID((long) roundScore.getScorecard().getId().intValue());
        dto.setRoundNumber(roundScore.getRoundNumber());
        dto.setFighterCorner(roundScore.getFighterCorner());
        dto.setScore(roundScore.getScore());
        return dto;
    }

    private ScoreCard convertToEntity(ScoreCardDTO dto) {
        ScoreCard scorecard = new ScoreCard();

        Fight fight = fightRepository.findById(dto.getFightID())
                .orElseThrow(() -> new ResourceNotFoundException("Fight", Long.valueOf(dto.getFightID())));
        scorecard.setFight(fight);

        Judge judge = judgeRepository.findById((long) dto.getJudgeID())
                .orElseThrow(() -> new ResourceNotFoundException("Judge", dto.getJudgeID()));
        scorecard.setJudge(judge);

        scorecard.setComments(dto.getComments());

        return scorecard;
    }
}
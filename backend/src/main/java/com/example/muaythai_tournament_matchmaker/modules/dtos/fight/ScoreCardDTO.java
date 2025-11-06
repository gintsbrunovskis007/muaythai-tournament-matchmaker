package com.example.muaythai_tournament_matchmaker.modules.dtos.fight;

import java.util.List;

public class ScoreCardDTO {

    private Long fightID;
    private Long judgeID;
    private List<RoundScoreDTO> roundScores;
    private String comments;

    public ScoreCardDTO(){};

    public ScoreCardDTO(Long fightID, Long judgeID, List<RoundScoreDTO> roundScores, String comments) {
        this.fightID = fightID;
        this.judgeID = judgeID;
        this.roundScores = roundScores;
        this.comments = comments;
    }

    public Long getFightID() {
        return fightID;
    }

    public void setFightID(Long fightID) {
        this.fightID = fightID;
    }

    public Long getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(Long judgeID) {
        this.judgeID = judgeID;
    }

    public List<RoundScoreDTO> getRoundScores() {
        return roundScores;
    }

    public void setRoundScores(List<RoundScoreDTO> roundScores) {
        this.roundScores = roundScores;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

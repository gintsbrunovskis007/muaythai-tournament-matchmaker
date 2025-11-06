package com.example.muaythai_tournament_matchmaker.modules.dtos.fight;

import com.example.muaythai_tournament_matchmaker.models.fight.Corner;

public class RoundScoreDTO {

    private Long scoreCardID;
    private Integer roundNumber;
    private Corner fighterCorner;
    private Integer score;

    public RoundScoreDTO(){};

    public RoundScoreDTO(Long scoreCardID, Integer roundNumber, Corner fighterCorner, Integer score) {
        this.scoreCardID = scoreCardID;
        this.roundNumber = roundNumber;
        this.fighterCorner = fighterCorner;
        this.score = score;
    }

    public Long getScoreCardID() {
        return scoreCardID;
    }

    public void setScoreCardID(Long scoreCardID) {
        this.scoreCardID = scoreCardID;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Corner getFighterCorner() {
        return fighterCorner;
    }

    public void setFighterCorner(Corner fighterCorner) {
        this.fighterCorner = fighterCorner;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}


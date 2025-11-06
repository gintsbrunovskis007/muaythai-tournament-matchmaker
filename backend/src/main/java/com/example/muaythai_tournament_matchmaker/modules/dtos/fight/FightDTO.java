package com.example.muaythai_tournament_matchmaker.modules.dtos.fight;

import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.JudgeDTO;

import java.util.List;

public class FightDTO {

    private Long id;
    private Long eventID;
    private Long redCornerFighterID;
    private Long blueCornerFighterID;
    private Long refereeID;
    private List<Long> judgesIds;
    private Long winnerID;
    private String result;
    private String method;
    private Integer totalRounds;
    private Integer roundFinished;
    private Double finishTime;
    private List<ScoreCardDTO> scorecards;

    public FightDTO(){};

    public FightDTO(Long id, Long eventID, Long redCornerFighterID, Long blueCornerFighterID, Long refereeID, List<Long> judgesIds, Long winnerID, String result, String method, Integer totalRounds, Integer roundFinished, Double finishTime, List<ScoreCardDTO> scorecards) {
        this.id = id;
        this.eventID = eventID;
        this.redCornerFighterID = redCornerFighterID;
        this.blueCornerFighterID = blueCornerFighterID;
        this.refereeID = refereeID;
        this.judgesIds = judgesIds;
        this.winnerID = winnerID;
        this.result = result;
        this.method = method;
        this.totalRounds = totalRounds;
        this.roundFinished = roundFinished;
        this.finishTime = finishTime;
        this.scorecards = scorecards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getRedCornerFighterID() {
        return redCornerFighterID;
    }

    public void setRedCornerFighterID(Long redCornerFighterID) {
        this.redCornerFighterID = redCornerFighterID;
    }

    public Long getBlueCornerFighterID() {
        return blueCornerFighterID;
    }

    public void setBlueCornerFighterID(Long blueCornerFighterID) {
        this.blueCornerFighterID = blueCornerFighterID;
    }

    public Long getRefereeID() {
        return refereeID;
    }

    public void setRefereeID(Long refereeID) {
        this.refereeID = refereeID;
    }

    public List<Long> getJudgesIds() {
        return judgesIds;
    }

    public void setJudgesIds(List<Long> judgesIds) {
        this.judgesIds = judgesIds;
    }

    public Long getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(Long winnerID) {
        this.winnerID = winnerID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(Integer totalRounds) {
        this.totalRounds = totalRounds;
    }

    public Integer getRoundFinished() {
        return roundFinished;
    }

    public void setRoundFinished(Integer roundFinished) {
        this.roundFinished = roundFinished;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public List<ScoreCardDTO> getScorecards() {
        return scorecards;
    }

    public void setScorecards(List<ScoreCardDTO> scorecards) {
        this.scorecards = scorecards;
    }
}

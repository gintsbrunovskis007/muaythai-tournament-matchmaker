package com.example.muaythai_tournament_matchmaker.modules.dtos.tournament;

public class StatisticsDTO {

    private Long fighterId;
    private Long fightId;

    private int punchesAttempted;
    private int punchesLanded;

    private int kicksAttempted;
    private int kicksLanded;

    private int elbowAttempted;
    private int elbowLanded;

    private int kneeAttempted;
    private int kneeLanded;

    private int headStrikesAttempted;
    private int headStrikesLanded;

    private int bodyStrikesAttempted;
    private int bodyStrikesLanded;

    private int legStrikesAttempted;
    private int legStrikesLanded;

    private int knockDowns;
    private int knockOuts;
    private int technicalKnockOuts;

    private int clinchControlTime;
    private int fouls;

    public StatisticsDTO(){};

    public StatisticsDTO(Long fighterId, Long fightId, int punchesAttempted, int punchesLanded, int kicksAttempted, int kicksLanded, int elbowAttempted, int elbowLanded, int kneeAttempted, int kneeLanded, int headStrikesAttempted, int headStrikesLanded, int bodyStrikesAttempted, int bodyStrikesLanded, int legStrikesAttempted, int legStrikesLanded, int knockDowns, int knockOuts, int technicalKnockOuts, int clinchControlTime, int fouls) {
        this.fighterId = fighterId;
        this.fightId = fightId;
        this.punchesAttempted = punchesAttempted;
        this.punchesLanded = punchesLanded;
        this.kicksAttempted = kicksAttempted;
        this.kicksLanded = kicksLanded;
        this.elbowAttempted = elbowAttempted;
        this.elbowLanded = elbowLanded;
        this.kneeAttempted = kneeAttempted;
        this.kneeLanded = kneeLanded;
        this.headStrikesAttempted = headStrikesAttempted;
        this.headStrikesLanded = headStrikesLanded;
        this.bodyStrikesAttempted = bodyStrikesAttempted;
        this.bodyStrikesLanded = bodyStrikesLanded;
        this.legStrikesAttempted = legStrikesAttempted;
        this.legStrikesLanded = legStrikesLanded;
        this.knockDowns = knockDowns;
        this.knockOuts = knockOuts;
        this.technicalKnockOuts = technicalKnockOuts;
        this.clinchControlTime = clinchControlTime;
        this.fouls = fouls;
    }

    public Long getFighterId() {
        return fighterId;
    }

    public void setFighterId(Long fighterId) {
        this.fighterId = fighterId;
    }

    public Long getFightId() {
        return fightId;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public int getPunchesAttempted() {
        return punchesAttempted;
    }

    public void setPunchesAttempted(int punchesAttempted) {
        this.punchesAttempted = punchesAttempted;
    }

    public int getPunchesLanded() {
        return punchesLanded;
    }

    public void setPunchesLanded(int punchesLanded) {
        this.punchesLanded = punchesLanded;
    }

    public int getKicksAttempted() {
        return kicksAttempted;
    }

    public void setKicksAttempted(int kicksAttempted) {
        this.kicksAttempted = kicksAttempted;
    }

    public int getKicksLanded() {
        return kicksLanded;
    }

    public void setKicksLanded(int kicksLanded) {
        this.kicksLanded = kicksLanded;
    }

    public int getElbowAttempted() {
        return elbowAttempted;
    }

    public void setElbowAttempted(int elbowAttempted) {
        this.elbowAttempted = elbowAttempted;
    }

    public int getElbowLanded() {
        return elbowLanded;
    }

    public void setElbowLanded(int elbowLanded) {
        this.elbowLanded = elbowLanded;
    }

    public int getKneeAttempted() {
        return kneeAttempted;
    }

    public void setKneeAttempted(int kneeAttempted) {
        this.kneeAttempted = kneeAttempted;
    }

    public int getKneeLanded() {
        return kneeLanded;
    }

    public void setKneeLanded(int kneeLanded) {
        this.kneeLanded = kneeLanded;
    }

    public int getHeadStrikesAttempted() {
        return headStrikesAttempted;
    }

    public void setHeadStrikesAttempted(int headStrikesAttempted) {
        this.headStrikesAttempted = headStrikesAttempted;
    }

    public int getHeadStrikesLanded() {
        return headStrikesLanded;
    }

    public void setHeadStrikesLanded(int headStrikesLanded) {
        this.headStrikesLanded = headStrikesLanded;
    }

    public int getBodyStrikesAttempted() {
        return bodyStrikesAttempted;
    }

    public void setBodyStrikesAttempted(int bodyStrikesAttempted) {
        this.bodyStrikesAttempted = bodyStrikesAttempted;
    }

    public int getBodyStrikesLanded() {
        return bodyStrikesLanded;
    }

    public void setBodyStrikesLanded(int bodyStrikesLanded) {
        this.bodyStrikesLanded = bodyStrikesLanded;
    }

    public int getLegStrikesAttempted() {
        return legStrikesAttempted;
    }

    public void setLegStrikesAttempted(int legStrikesAttempted) {
        this.legStrikesAttempted = legStrikesAttempted;
    }

    public int getLegStrikesLanded() {
        return legStrikesLanded;
    }

    public void setLegStrikesLanded(int legStrikesLanded) {
        this.legStrikesLanded = legStrikesLanded;
    }

    public int getKnockDowns() {
        return knockDowns;
    }

    public void setKnockDowns(int knockDowns) {
        this.knockDowns = knockDowns;
    }

    public int getKnockOuts() {
        return knockOuts;
    }

    public void setKnockOuts(int knockOuts) {
        this.knockOuts = knockOuts;
    }

    public int getTechnicalKnockOuts() {
        return technicalKnockOuts;
    }

    public void setTechnicalKnockOuts(int technicalKnockOuts) {
        this.technicalKnockOuts = technicalKnockOuts;
    }

    public int getClinchControlTime() {
        return clinchControlTime;
    }

    public void setClinchControlTime(int clinchControlTime) {
        this.clinchControlTime = clinchControlTime;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }
}

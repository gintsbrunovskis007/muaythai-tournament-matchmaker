package com.example.muaythai_tournament_matchmaker.modules.dtos.fighter;

import com.example.muaythai_tournament_matchmaker.models.tournament.Role;
import com.example.muaythai_tournament_matchmaker.models.tournament.WeightClass;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.StatisticsDTO;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FighterDTO {

    // Identity
    IdentityDTO identity;

    // Body measurements
    private BigDecimal weight;
    private BigDecimal height;
    private BigDecimal wingspan;
    private BigDecimal legReach;

    // Fight record
    private int wins;
    private int losses;
    private int draws;
    private int noContests;

    // References
    private Long gymID;
    private Long statisticsID;

    // Image references (or include as base64 strings)
    private String fullHeightImage;
    private String headToWaistImage;
    private String closeUpImage;

    // Weight class
    private WeightClass weightClass;

    public FighterDTO(){};

    public FighterDTO(IdentityDTO identity, BigDecimal weight, BigDecimal height, BigDecimal wingspan, BigDecimal legReach, int wins, int losses, int draws, int noContests, Long gymID, Long statisticsID, String fullHeightImage, String headToWaistImage, String closeUpImage, WeightClass weightClass) {
        this.identity = identity;
        this.weight = weight;
        this.height = height;
        this.wingspan = wingspan;
        this.legReach = legReach;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.noContests = noContests;
        this.gymID = gymID;
        this.statisticsID = statisticsID;
        this.fullHeightImage = fullHeightImage;
        this.headToWaistImage = headToWaistImage;
        this.closeUpImage = closeUpImage;
        this.weightClass = weightClass;
    }

    public IdentityDTO getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityDTO identity) {
        this.identity = identity;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWingspan() {
        return wingspan;
    }

    public void setWingspan(BigDecimal wingspan) {
        this.wingspan = wingspan;
    }

    public BigDecimal getLegReach() {
        return legReach;
    }

    public void setLegReach(BigDecimal legReach) {
        this.legReach = legReach;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getNoContests() {
        return noContests;
    }

    public void setNoContests(int noContests) {
        this.noContests = noContests;
    }

    public Long getGymID() {
        return gymID;
    }

    public void setGymID(Long gymID) {
        this.gymID = gymID;
    }

    public Long getStatisticsID() {
        return statisticsID;
    }

    public void setStatisticsID(Long statisticsID) {
        this.statisticsID = statisticsID;
    }

    public String getFullHeightImage() {
        return fullHeightImage;
    }

    public void setFullHeightImage(String fullHeightImage) {
        this.fullHeightImage = fullHeightImage;
    }

    public String getHeadToWaistImage() {
        return headToWaistImage;
    }

    public void setHeadToWaistImage(String headToWaistImage) {
        this.headToWaistImage = headToWaistImage;
    }

    public String getCloseUpImage() {
        return closeUpImage;
    }

    public void setCloseUpImage(String closeUpImage) {
        this.closeUpImage = closeUpImage;
    }

    public WeightClass getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(WeightClass weightClass) {
        this.weightClass = weightClass;
    }
}

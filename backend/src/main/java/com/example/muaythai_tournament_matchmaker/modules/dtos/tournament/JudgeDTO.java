package com.example.muaythai_tournament_matchmaker.modules.dtos.tournament;

import com.example.muaythai_tournament_matchmaker.models.tournament.Role;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.ScoreCardDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;

import java.time.LocalDate;
import java.util.List;

public class JudgeDTO {

    private IdentityDTO identity;

    private List<EventDTO> events;
    private List<ScoreCardDTO> scoreCardDTOS;

    private String profilePicture;
    private Integer yearsOfExperience;

    public JudgeDTO(){};

    public JudgeDTO(IdentityDTO identity, List<EventDTO> events, List<ScoreCardDTO> scoreCardDTOS, String profilePicture, Integer yearsOfExperience) {
        this.identity = identity;
        this.events = events;
        this.scoreCardDTOS = scoreCardDTOS;
        this.profilePicture = profilePicture;
        this.yearsOfExperience = yearsOfExperience;
    }

    public IdentityDTO getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityDTO identity) {
        this.identity = identity;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    public List<ScoreCardDTO> getScoreCardDTOS() {
        return scoreCardDTOS;
    }

    public void setScoreCardDTOS(List<ScoreCardDTO> scoreCardDTOS) {
        this.scoreCardDTOS = scoreCardDTOS;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}

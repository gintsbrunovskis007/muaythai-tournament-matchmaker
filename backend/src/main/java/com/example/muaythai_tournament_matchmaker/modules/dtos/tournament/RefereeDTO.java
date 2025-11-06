package com.example.muaythai_tournament_matchmaker.modules.dtos.tournament;

import com.example.muaythai_tournament_matchmaker.models.tournament.Role;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.FightDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;

import java.time.LocalDate;
import java.util.List;

public class RefereeDTO {

    private IdentityDTO identity;

    private List<EventDTO> events;
    private List<FightDTO> fights;

    private String profilePicture;
    private Integer yearsOfExperience;

    public RefereeDTO(){};

    public RefereeDTO(IdentityDTO identity, List<EventDTO> events, List<FightDTO> fights, String profilePicture, Integer yearsOfExperience) {
        this.identity = identity;
        this.events = events;
        this.fights = fights;
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

    public List<FightDTO> getFights() {
        return fights;
    }

    public void setFights(List<FightDTO> fights) {
        this.fights = fights;
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

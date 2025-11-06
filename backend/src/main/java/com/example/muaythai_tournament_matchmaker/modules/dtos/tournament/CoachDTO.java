package com.example.muaythai_tournament_matchmaker.modules.dtos.tournament;

import com.example.muaythai_tournament_matchmaker.models.tournament.Role;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;

import java.time.LocalDate;

public class CoachDTO {

    private IdentityDTO identity;

    private Long gymID;

    private String profilePicture;

    public CoachDTO() {};

    public CoachDTO(IdentityDTO identity, Long gymID, String profilePicture) {
        this.identity = identity;
        this.gymID = gymID;
        this.profilePicture = profilePicture;
    }

    public IdentityDTO getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityDTO identity) {
        this.identity = identity;
    }

    public Long getGymID() {
        return gymID;
    }

    public void setGymID(Long gymID) {
        this.gymID = gymID;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

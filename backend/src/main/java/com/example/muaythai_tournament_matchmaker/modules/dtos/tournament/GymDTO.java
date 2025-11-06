package com.example.muaythai_tournament_matchmaker.modules.dtos.tournament;

import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.FighterDTO;

import java.time.LocalDate;
import java.util.List;

public class GymDTO {

    private String name;
    private String address;
    private Long headCoachID;
    private List<Long> fighters;
    private LocalDate established;
    private String logo;
    private String description;

    public GymDTO(){};

    public GymDTO(String name, String address, Long headCoachID, List<Long> fighters, LocalDate established, String logo, String description) {
        this.name = name;
        this.address = address;
        this.headCoachID = headCoachID;
        this.fighters = fighters;
        this.established = established;
        this.logo = logo;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getHeadCoachID() {
        return headCoachID;
    }

    public void setHeadCoachID(Long headCoachID) {
        this.headCoachID = headCoachID;
    }

    public List<Long> getFighters() {
        return fighters;
    }

    public void setFighters(List<Long> fighters) {
        this.fighters = fighters;
    }

    public LocalDate getEstablished() {
        return established;
    }

    public void setEstablished(LocalDate established) {
        this.established = established;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.muaythai_tournament_matchmaker.modules.dtos.tournament;

import com.example.muaythai_tournament_matchmaker.modules.dtos.fight.FightDTO;

import java.time.LocalDate;
import java.util.List;

public class EventDTO {

    private String name;
    private LocalDate date;
    private String location;
    private List<FightDTO> fights;
    private String description;

    public EventDTO(){};

    public EventDTO(String name, LocalDate date, String location, List<FightDTO> fights, String description) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.fights = fights;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<FightDTO> getFights() {
        return fights;
    }

    public void setFights(List<FightDTO> fights) {
        this.fights = fights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

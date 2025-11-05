package com.example.muaythai_tournament_matchmaker.models.tournament;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Event name is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Event date is required")
    private LocalDate date;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Event location is required")
    private String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fight> fights = new ArrayList<>();

    @Column(length = 1000)
    private String description;

    public Event(){};

    public Event(Long id, String name, LocalDate date, String location, List<Fight> fights, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.fights = fights;
        this.description = description;
    }

    public void addFight(Fight fight) {
        fights.add(fight);
        fight.setEvent(this);
    }

    public void removeFight(Fight fight) {
        fights.remove(fight);
        fight.setEvent(null);
    }

    public int getTotalFights() {
        return fights.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Fight> getFights() {
        return fights;
    }

    public void setFights(List<Fight> fights) {
        this.fights = fights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.muaythai_tournament_matchmaker.models.tournament;

import java.util.ArrayList;

import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import com.example.muaythai_tournament_matchmaker.models.fight.Scorecard;
import com.example.muaythai_tournament_matchmaker.models.fighter.Identity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "judges")
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "identity_id")
    @NotNull(message = "Identity is required")
    private Identity identity;

    @ManyToMany
    @JoinTable(
            name = "judge_events",
            joinColumns = @JoinColumn(name = "judge_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<>();

    @ManyToMany(mappedBy = "judges")
    private List<Fight> fights = new ArrayList<>();

    @OneToMany(mappedBy = "judge")
    private List<Scorecard> scorecards = new ArrayList<>();

    @Column(length = 500)
    private String profilePicture;

    @Column(name = "years_of_experience")
    @Min(value = 0, message = "Years of experience cannot be negative")
    private Integer yearsOfExperience;

    public Judge(){};

    public Judge(Long id, Identity identity, List<Event> events, List<Fight> fights, List<Scorecard> scorecards, String profilePicture, Integer yearsOfExperience) {
        this.id = id;
        this.identity = identity;
        this.events = events;
        this.fights = fights;
        this.scorecards = scorecards;
        this.profilePicture = profilePicture;
        this.yearsOfExperience = yearsOfExperience;
    }

    public void addEvent(Event event) {
        if (!events.contains(event)) {
            events.add(event);
        }
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void addFight(Fight fight) {
        if (!fights.contains(fight)) {
            fights.add(fight);
        }
    }

    public void removeFight(Fight fight) {
        fights.remove(fight);
    }

    public void addScorecard(Scorecard scorecard) {
        scorecards.add(scorecard);
        scorecard.setJudge(this);
        if (scorecard.getFight() != null && !fights.contains(scorecard.getFight())) {
            fights.add(scorecard.getFight());
        }
    }

    public void removeScorecard(Scorecard scorecard) {
        scorecards.remove(scorecard);
        scorecard.setJudge(null);
    }

    public int getTotalFightsJudged() {
        return fights.size();
    }

    public int getTotalEvents() {
        return events.size();
    }

    public String getName() {
        return identity != null ? identity.getFirstName() + " " + identity.getLastName() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Identity is required") Identity getIdentity() {
        return identity;
    }

    public void setIdentity(@NotNull(message = "Identity is required") Identity identity) {
        this.identity = identity;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Fight> getFights() {
        return fights;
    }

    public void setFights(List<Fight> fights) {
        this.fights = fights;
    }

    public List<Scorecard> getScorecards() {
        return scorecards;
    }

    public void setScorecards(List<Scorecard> scorecards) {
        this.scorecards = scorecards;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public @Min(value = 0, message = "Years of experience cannot be negative") Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(@Min(value = 0, message = "Years of experience cannot be negative") Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}

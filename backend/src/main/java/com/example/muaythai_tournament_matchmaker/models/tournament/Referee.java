package com.example.muaythai_tournament_matchmaker.models.tournament;

import java.util.ArrayList;
import java.util.List;

import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import com.example.muaythai_tournament_matchmaker.models.fighter.Identity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "referees")
public class Referee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "identity_id")
  @NotNull(message = "Identity is required")
  private Identity identity;

  @ManyToMany
  @JoinTable(
          name = "referee_events",
          joinColumns = @JoinColumn(name = "referee_id"),
          inverseJoinColumns = @JoinColumn(name = "event_id")
  )
  private List<Event> events = new ArrayList<>();

  @OneToMany(mappedBy = "referee")
  private List<Fight> fights = new ArrayList<>();

  @Column(length = 500)
  private String profilePicture;

  @Column(name = "years_of_experience")
  @Min(value = 0, message = "Years of experience cannot be negative")
  private Integer yearsOfExperience;

  public Referee(){};

  public Referee(Long id, Identity identity, List<Event> events, List<Fight> fights, String profilePicture, Integer yearsOfExperience) {
    this.id = id;
    this.identity = identity;
    this.events = events;
    this.fights = fights;
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
    fights.add(fight);
    fight.setReferee(this);
  }

  public void removeFight(Fight fight) {
    fights.remove(fight);
    fight.setReferee(null);
  }

  public int getTotalFightsRefereed() {
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

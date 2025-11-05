package com.example.muaythai_tournament_matchmaker.models.tournament;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.example.muaythai_tournament_matchmaker.models.fighter.Fighter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "gyms")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Gym name is required")
    private String name;

    @Column(length = 255)
    private String address;

    @OneToOne
    @JoinColumn(name = "head_coach_id")
    private Coach headCoach;

    @OneToMany(mappedBy = "gym")
    private List<Fighter> fighters = new ArrayList<>();

    @Column(name = "established_date")
    private LocalDate established;

    @Column(length = 500)
    private String logo;

    @Column(length = 1000)
    private String description;

    public Gym(){};

    public Gym(Long id, String name, String address, Coach headCoach, List<Fighter> fighters, LocalDate established, String logo, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.headCoach = headCoach;
        this.fighters = fighters;
        this.established = established;
        this.logo = logo;
        this.description = description;
    }

    public void addFighter(Fighter fighter) {
        fighters.add(fighter);
        fighter.setGym(this);
    }

    public void removeFighter(Fighter fighter) {
        fighters.remove(fighter);
        fighter.setGym(null);
    }

    public int getTotalFighters() {
        return fighters.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Gym name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Gym name is required") String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(Coach headCoach) {
        this.headCoach = headCoach;
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(List<Fighter> fighters) {
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

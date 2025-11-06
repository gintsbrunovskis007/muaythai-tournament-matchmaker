package com.example.muaythai_tournament_matchmaker.models.fighter;

import java.util.ArrayList;
import java.util.List;

import com.example.muaythai_tournament_matchmaker.models.tournament.Gym;
import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import com.example.muaythai_tournament_matchmaker.models.tournament.Statistics;
import com.example.muaythai_tournament_matchmaker.models.tournament.WeightClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "fighters")
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "identity_id")
    @NotNull(message = "Identity is required")
    private Identity identity;

    @Embedded
    private FighterImage fighterImage;

    @Embedded
    @NotNull(message = "Body measurements are required")
    private Body body;

    @Embedded
    private Record fightRecord;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @OneToMany(mappedBy = "redCorner")
    private List<Fight> redCornerFights = new ArrayList<>();

    @OneToMany(mappedBy = "blueCorner")
    private List<Fight> blueCornerFights = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    @Enumerated(EnumType.STRING)
    private WeightClass weightClass;

    public Fighter(){};

    public Fighter(Long id, Identity identity, FighterImage fighterImage, Body body, Record fightRecord, Gym gym, List<Fight> redCornerFights, List<Fight> blueCornerFights, Statistics statistics, WeightClass weightClass) {
        this.id = id;
        this.identity = identity;
        this.fighterImage = fighterImage;
        this.body = body;
        this.fightRecord = fightRecord;
        this.gym = gym;
        this.redCornerFights = redCornerFights;
        this.blueCornerFights = blueCornerFights;
        this.statistics = statistics;
        this.weightClass = weightClass;
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

    public FighterImage getFighterImage() {
        return fighterImage;
    }

    public void setFighterImage(FighterImage fighterImage) {
        this.fighterImage = fighterImage;
    }

    public @NotNull(message = "Body measurements are required") Body getBody() {
        return body;
    }

    public void setBody(@NotNull(message = "Body measurements are required") Body body) {
        this.body = body;
    }

    public Record getFightRecord() {
        return fightRecord;
    }

    public void setFightRecord(Record fightRecord) {
        this.fightRecord = fightRecord;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public List<Fight> getRedCornerFights() {
        return redCornerFights;
    }

    public void setRedCornerFights(List<Fight> redCornerFights) {
        this.redCornerFights = redCornerFights;
    }

    public List<Fight> getBlueCornerFights() {
        return blueCornerFights;
    }

    public void setBlueCornerFights(List<Fight> blueCornerFights) {
        this.blueCornerFights = blueCornerFights;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public WeightClass getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(WeightClass weightClass) {
        this.weightClass = weightClass;
    }
}

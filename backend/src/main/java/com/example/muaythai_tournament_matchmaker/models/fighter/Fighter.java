package com.example.muaythai_tournament_matchmaker.models.fighter;

import java.util.ArrayList;
import java.util.List;

import com.example.muaythai_tournament_matchmaker.models.tournament.Gym;
import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fighter_image_id")
    private FighterImage fighterImage;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "body_id")
    @NotNull(message = "Body measurements are required")
    private Body body;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fighter_record_id")
    private Record fightRecord;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @OneToMany(mappedBy = "redCorner")
    private List<Fight> redCornerFights = new ArrayList<>();

    @OneToMany(mappedBy = "blueCorner")
    private List<Fight> blueCornerFights = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private WeightClass weightClass;

    public Fighter(){};

    public List<Fight> getAllFights() {
        List<Fight> allFights = new ArrayList<>();
        allFights.addAll(redCornerFights);
        allFights.addAll(blueCornerFights);
        return allFights;
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

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public FighterImage getFighterImage() {
        return fighterImage;
    }

    public void setFighterImage(FighterImage fighterImage) {
        this.fighterImage = fighterImage;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
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
}

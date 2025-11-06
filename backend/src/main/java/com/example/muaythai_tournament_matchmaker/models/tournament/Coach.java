package com.example.muaythai_tournament_matchmaker.models.tournament;

import com.example.muaythai_tournament_matchmaker.models.fighter.Identity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "identity_id")
    @NotNull(message = "Identity is required")
    private Identity identity;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Column(length = 500)
    private String profilePicture;

    public Coach(){};

    public Coach(Long id, Identity identity, Gym gym, String profilePicture) {
        this.id = id;
        this.identity = identity;
        this.gym = gym;
        this.profilePicture = profilePicture;
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

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

package com.example.muaythai_tournament_matchmaker.models.fight;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "round_scores")
public class RoundScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "scorecard_id", nullable = false)
    @NotNull(message = "Scorecard is required")
    private ScoreCard scorecard;
    
    @Column(nullable = false)
    @NotNull(message = "Round number is required")
    @Min(value = 1, message = "Round number must be at least 1")
    private Integer roundNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Fighter corner is required")
    private Corner fighterCorner;
    
    @Column(nullable = false)
    @NotNull(message = "Score is required")
    @Min(value = 6, message = "Score must be at least 6")
    @Max(value = 10, message = "Score must not exceed 10")
    private Integer score;

    public RoundScore(){};

    public RoundScore(Long id, ScoreCard scorecard, Integer roundNumber, Corner fighterCorner, Integer score) {
        this.id = id;
        this.scorecard = scorecard;
        this.roundNumber = roundNumber;
        this.fighterCorner = fighterCorner;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Scorecard is required") ScoreCard getScorecard() {
        return scorecard;
    }

    public void setScorecard(@NotNull(message = "Scorecard is required") ScoreCard scorecard) {
        this.scorecard = scorecard;
    }

    public @NotNull(message = "Round number is required") @Min(value = 1, message = "Round number must be at least 1") Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(@NotNull(message = "Round number is required") @Min(value = 1, message = "Round number must be at least 1") Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public @NotNull(message = "Fighter corner is required") Corner getFighterCorner() {
        return fighterCorner;
    }

    public void setFighterCorner(@NotNull(message = "Fighter corner is required") Corner fighterCorner) {
        this.fighterCorner = fighterCorner;
    }

    public @NotNull(message = "Score is required") @Min(value = 6, message = "Score must be at least 6") @Max(value = 10, message = "Score must not exceed 10") Integer getScore() {
        return score;
    }

    public void setScore(@NotNull(message = "Score is required") @Min(value = 6, message = "Score must be at least 6") @Max(value = 10, message = "Score must not exceed 10") Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "RoundScore{" +
                "id=" + id +
                ", scorecard=" + scorecard +
                ", roundNumber=" + roundNumber +
                ", fighterCorner=" + fighterCorner +
                ", score=" + score +
                '}';
    }
}

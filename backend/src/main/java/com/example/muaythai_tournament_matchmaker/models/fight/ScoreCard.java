package com.example.muaythai_tournament_matchmaker.models.fight;

import com.example.muaythai_tournament_matchmaker.models.tournament.Judge;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scorecards")
public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fight_id", nullable = false)
    @NotNull(message = "Fight is required")
    private Fight fight;

    @ManyToOne
    @JoinColumn(name = "judge_id", nullable = false)
    @NotNull(message = "Judge is required")
    private Judge judge;

    @OneToMany(mappedBy = "scorecard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoundScore> roundScores = new ArrayList<>();

    @Column(length = 1000)
    private String comments;

    @Column(name = "total_score_red")
    private Integer totalScoreRed;

    @Column(name = "total_score_blue")
    private Integer totalScoreBlue;

    public ScoreCard(){};

    public ScoreCard(Long id, Fight fight, Judge judge, List<RoundScore> roundScores, String comments, Integer totalScoreRed, Integer totalScoreBlue) {
        this.id = id;
        this.fight = fight;
        this.judge = judge;
        this.roundScores = roundScores;
        this.comments = comments;
        this.totalScoreRed = totalScoreRed;
        this.totalScoreBlue = totalScoreBlue;
    }

    public void addRoundScore(RoundScore roundScore) {
        roundScores.add(roundScore);
        roundScore.setScorecard(this);
    }

    public void removeRoundScore(RoundScore roundScore) {
        roundScores.remove(roundScore);
        roundScore.setScorecard(null);
    }

    public void calculateTotalScores() {
        totalScoreRed = roundScores.stream()
                .filter(rs -> rs.getFighterCorner() == Corner.RED)
                .mapToInt(RoundScore::getScore)
                .sum();
        totalScoreBlue = roundScores.stream()
                .filter(rs -> rs.getFighterCorner() == Corner.BLUE)
                .mapToInt(RoundScore::getScore)
                .sum();
    }

    public Corner getWinnerCorner() {
        if (totalScoreRed == null || totalScoreBlue == null) {
            if (roundScores == null || roundScores.isEmpty()) {
                return null;
            }
            calculateTotalScores();
        }
        if (totalScoreRed > totalScoreBlue) {
            return Corner.RED;
        } else if (totalScoreBlue > totalScoreRed) {
            return Corner.BLUE;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Fight is required") Fight getFight() {
        return fight;
    }

    public void setFight(@NotNull(message = "Fight is required") Fight fight) {
        this.fight = fight;
    }

    public @NotNull(message = "Judge is required") Judge getJudge() {
        return judge;
    }

    public void setJudge(@NotNull(message = "Judge is required") Judge judge) {
        this.judge = judge;
    }

    public List<RoundScore> getRoundScores() {
        return roundScores;
    }

    public void setRoundScores(List<RoundScore> roundScores) {
        this.roundScores = roundScores;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getTotalScoreRed() {
        return totalScoreRed;
    }

    public void setTotalScoreRed(Integer totalScoreRed) {
        this.totalScoreRed = totalScoreRed;
    }

    public Integer getTotalScoreBlue() {
        return totalScoreBlue;
    }

    public void setTotalScoreBlue(Integer totalScoreBlue) {
        this.totalScoreBlue = totalScoreBlue;
    }

    @Override
    public String toString() {
        return "Scorecard{" +
                "id=" + id +
                ", fight=" + fight +
                ", judge=" + judge +
                ", roundScores=" + roundScores +
                ", comments='" + comments + '\'' +
                ", totalScoreRed=" + totalScoreRed +
                ", totalScoreBlue=" + totalScoreBlue +
                '}';
    }
}

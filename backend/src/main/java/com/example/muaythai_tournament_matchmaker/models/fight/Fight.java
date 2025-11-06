package com.example.muaythai_tournament_matchmaker.models.fight;

import java.util.ArrayList;
import java.util.List;
import com.example.muaythai_tournament_matchmaker.models.tournament.Event;
import com.example.muaythai_tournament_matchmaker.models.tournament.Judge;
import com.example.muaythai_tournament_matchmaker.models.tournament.Referee;
import com.example.muaythai_tournament_matchmaker.models.fighter.Fighter;
import com.example.muaythai_tournament_matchmaker.models.tournament.Statistics;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fights")
public class Fight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull(message = "Event is required")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "red_corner_fighter_id", nullable = false)
    @NotNull(message = "Red corner fighter is required")
    private Fighter redCorner;

    @ManyToOne
    @JoinColumn(name = "blue_corner_fighter_id", nullable = false)
    @NotNull(message = "Blue corner fighter is required")
    private Fighter blueCorner;

    @OneToOne
    @JoinColumn(name = "referee_id")
    @NotNull(message = "Referee is required")
    private Referee referee;

    @ManyToMany
    @JoinTable(
            name = "fight_judges",
            joinColumns = @JoinColumn(name = "fight_id"),
            inverseJoinColumns = @JoinColumn(name = "judge_id")
    )
    @Size(min = 3, max = 3, message = "Must have exactly 3 judges")
    private List<Judge> judges = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Fighter winner;

    @OneToOne
    @JoinColumn(name = "red_statistics_id")
    private Statistics redStatistics;

    @OneToOne
    @JoinColumn(name = "blue_statistics_id")
    private Statistics blueStatistics;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private Result result;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private Method method;

    @Column(name = "total_rounds", nullable = false)
    @NotNull(message = "Total rounds is required")
    @Min(value = 1, message = "Total rounds must be at least 1")
    private Integer totalRounds;

    @Column(name = "round_finished")
    private Integer roundFinished;

    @Column(name = "finish_time")
    private Double finishTime;

    @OneToMany(mappedBy = "fight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScoreCard> scorecards = new ArrayList<>();

    public Fight(){};

    public Fight(Long id, Event event, Fighter redCorner, Fighter blueCorner, Fighter winner, Result result, Method method, Integer totalRounds, Integer roundFinished, Double finishTime, List<ScoreCard> scorecards) {
        this.id = id;
        this.event = event;
        this.redCorner = redCorner;
        this.blueCorner = blueCorner;
        this.winner = winner;
        this.result = result;
        this.method = method;
        this.totalRounds = totalRounds;
        this.roundFinished = roundFinished;
        this.finishTime = finishTime;
        this.scorecards = scorecards;
    }

    public Fighter getFighterByCorner(Corner corner) {
        return corner == Corner.RED ? redCorner : blueCorner;
    }

    public Corner getCornerByFighter(Fighter fighter) {
        if (fighter != null) {
            if (fighter.equals(redCorner)) return Corner.RED;
            if (fighter.equals(blueCorner)) return Corner.BLUE;
        }
        return null;
    }

    public void addScorecard(ScoreCard scorecard) {
        scorecards.add(scorecard);
        scorecard.setFight(this);
    }

    public void removeScorecard(ScoreCard scorecard) {
        scorecards.remove(scorecard);
        scorecard.setFight(null);
    }

    public void determineWinner() {
        if (method != null && method != Method.DECISION) {
            return;
        }

        int redWins = 0;
        int blueWins = 0;
        int draws = 0;

        for (ScoreCard scorecard : scorecards) {
            scorecard.calculateTotalScores();
            Corner winnerCorner = scorecard.getWinnerCorner();

            if (winnerCorner == Corner.RED) {
                redWins++;
            } else if (winnerCorner == Corner.BLUE) {
                blueWins++;
            } else {
                draws++;
            }
        }

        if (redWins > blueWins) {
            this.winner = redCorner;
            if (redWins == scorecards.size()) {
                this.result = Result.UNANIMOUS_DECISION;
            } else if (redWins == blueWins + 1 && draws > 0) {
                this.result = Result.MAJORITY_DECISION;
            } else {
                this.result = Result.SPLIT_DECISION;
            }
        } else if (blueWins > redWins) {
            this.winner = blueCorner;
            if (blueWins == scorecards.size()) {
                this.result = Result.UNANIMOUS_DECISION;
            } else if (blueWins == redWins + 1 && draws > 0) {
                this.result = Result.MAJORITY_DECISION;
            } else {
                this.result = Result.SPLIT_DECISION;
            }
        } else {
            this.winner = null;
            this.result = Result.DRAW;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Event is required") Event getEvent() {
        return event;
    }

    public void setEvent(@NotNull(message = "Event is required") Event event) {
        this.event = event;
    }

    public @NotNull(message = "Red corner fighter is required") Fighter getRedCorner() {
        return redCorner;
    }

    public void setRedCorner(@NotNull(message = "Red corner fighter is required") Fighter redCorner) {
        this.redCorner = redCorner;
    }

    public @NotNull(message = "Blue corner fighter is required") Fighter getBlueCorner() {
        return blueCorner;
    }

    public void setBlueCorner(@NotNull(message = "Blue corner fighter is required") Fighter blueCorner) {
        this.blueCorner = blueCorner;
    }

    public Fighter getWinner() {
        return winner;
    }

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public @NotNull(message = "Total rounds is required") @Min(value = 1, message = "Total rounds must be at least 1") Integer getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(@NotNull(message = "Total rounds is required") @Min(value = 1, message = "Total rounds must be at least 1") Integer totalRounds) {
        this.totalRounds = totalRounds;
    }

    public Integer getRoundFinished() {
        return roundFinished;
    }

    public void setRoundFinished(Integer roundFinished) {
        this.roundFinished = roundFinished;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public List<ScoreCard> getScorecards() {
        return scorecards;
    }

    public void setScorecards(List<ScoreCard> scorecards) {
        this.scorecards = scorecards;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public void setJudges(List<Judge> judges) {
        this.judges = judges;
    }
}

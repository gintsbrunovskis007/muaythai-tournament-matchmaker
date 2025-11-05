package com.example.muaythai_tournament_matchmaker.models.fighter;

import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int wins;
    private int losses;
    private int draws;
    private int noContests;

    public Record(){};

    public Record(int wins, int losses, int draws, int noContests) {
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.noContests = noContests;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getNoContests() {
        return noContests;
    }

    public void setNoContests(int noContests) {
        this.noContests = noContests;
    }
}
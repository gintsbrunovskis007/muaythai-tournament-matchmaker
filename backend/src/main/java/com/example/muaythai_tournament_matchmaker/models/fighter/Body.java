package com.example.muaythai_tournament_matchmaker.models.fighter;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Embeddable
public class Body {

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(precision = 5, scale = 2)
    private BigDecimal height;

    @Column(precision = 5, scale = 2)
    private BigDecimal wingspan;

    @Column(precision = 5, scale = 2)
    private BigDecimal legReach;

    public Body(){};

    public Body(BigDecimal weight, BigDecimal height, BigDecimal wingspan, BigDecimal leg_reach) {
        this.weight = weight;
        this.height = height;
        this.wingspan = wingspan;
        this.legReach = legReach;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWingspan() {
        return wingspan;
    }

    public void setWingspan(BigDecimal wingspan) {
        this.wingspan = wingspan;
    }

    public BigDecimal getLeg_reach() {
        return legReach;
    }

    public void setLeg_reach(BigDecimal leg_reach) {
        this.legReach = leg_reach;
    }
}

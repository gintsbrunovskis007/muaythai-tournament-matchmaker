package com.example.muaythai_tournament_matchmaker.models.tournament;

public enum WeightClass {

    UNTIL_20KG(0, 20),
    UNTIL_25KG(20, 25),
    UNTIL_30KG(25, 30),
    UNTIL_35KG(30, 35),
    UNTIL_40KG(35, 40),
    UNTIL_45KG(40, 45),
    UNTIL_50KG(45, 50),
    UNTIL_55KG(50, 55),
    UNTIL_60KG(55, 60),
    UNTIL_65KG(60, 65),
    UNTIL_70KG(65, 70),
    UNTIL_75KG(70, 75),
    UNTIL_80KG(75, 80),
    UNTIL_85KG(80, 85),
    UNTIL_90KG(85, 90),
    UNTIL_95KG(90, 95),
    UNTIL_100KG(95, 100),
    HEAVYWEIGHT(100, 200);

    private final double minWeight;
    private final double maxWeight;

    WeightClass(double minWeight, double maxWeight) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public static WeightClass fromWeight(double weightKg) {
        for (WeightClass wc : values()) {
            if (weightKg > wc.minWeight && weightKg <= wc.maxWeight) {
                return wc;
            }
        }
        throw new IllegalArgumentException("No weight class for weight: " + weightKg);
    }
}

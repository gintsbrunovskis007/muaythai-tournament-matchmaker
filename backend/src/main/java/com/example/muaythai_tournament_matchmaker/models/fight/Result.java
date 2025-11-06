package com.example.muaythai_tournament_matchmaker.models.fight;

import jakarta.persistence.Embeddable;

public enum Result {
    UNANIMOUS_DECISION,
    SPLIT_DECISION,
    MAJORITY_DECISION,
    DRAW,
    KO,
    TKO,
    SUBMISSION,
    DISQUALIFICATION,
    NO_CONTEST;

}

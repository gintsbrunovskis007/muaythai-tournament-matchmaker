package com.example.muaythai_tournament_matchmaker.models.fight;

import jakarta.persistence.Embeddable;

public enum Method {
    DECISION,
    KO,
    TKO,
    SUBMISSION,
    DISQUALIFICATION,
    NO_CONTEST;

}


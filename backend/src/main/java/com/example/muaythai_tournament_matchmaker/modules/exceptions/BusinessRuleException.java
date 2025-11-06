package com.example.muaythai_tournament_matchmaker.modules.exceptions;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
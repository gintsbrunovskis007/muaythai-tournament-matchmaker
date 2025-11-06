package com.example.muaythai_tournament_matchmaker.modules.dtos.fighter;

import com.example.muaythai_tournament_matchmaker.models.tournament.Role;

import java.time.LocalDate;

public class IdentityDTO {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Role role;

    public IdentityDTO() {}

    public IdentityDTO(String firstName, String lastName, LocalDate dateOfBirth, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

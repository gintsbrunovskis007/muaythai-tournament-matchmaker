package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.tournament.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long> {
    Optional<Referee> findByIdentityId(Long identityId);
}
package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.tournament.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, Long> {
    Optional<Judge> findByIdentityId(Long identityId);
}
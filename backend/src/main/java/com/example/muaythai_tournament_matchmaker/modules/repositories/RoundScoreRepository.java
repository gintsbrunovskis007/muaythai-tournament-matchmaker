package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.fight.RoundScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundScoreRepository extends JpaRepository<RoundScore, Long> {
    List<RoundScore> findByScorecardId(Long scorecardId);
    List<RoundScore> findByScorecardFightId(Integer fightId);
}
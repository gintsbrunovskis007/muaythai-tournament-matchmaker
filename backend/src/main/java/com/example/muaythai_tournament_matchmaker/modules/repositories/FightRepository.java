package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.fight.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FightRepository extends JpaRepository<Fight, Long> {
    List<Fight> findByEventId(Long eventId);
    
    @Query("SELECT f FROM Fight f WHERE f.redCorner.id = :fighterId OR f.blueCorner.id = :fighterId")
    List<Fight> findByFighterId(@Param("fighterId") Long fighterId);
    
    @Query("SELECT f FROM Fight f WHERE f.winner.id = :fighterId")
    List<Fight> findWinsByFighterId(@Param("fighterId") Long fighterId);
    
    List<Fight> findByEventName(String eventName);
}
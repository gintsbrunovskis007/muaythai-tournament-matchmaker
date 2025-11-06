package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.tournament.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    List<Statistics> findByFighterId(Long fighterId);

    List<Statistics> findByFightId(Long fightId);

    Optional<Statistics> findByFightIdAndFighterId(Long fightId, Long fighterId);

    boolean existsByFightIdAndFighterId(Long fightId, Long fighterId);
}
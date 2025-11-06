package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.fight.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
    List<ScoreCard> findByFightId(Long fightId);
    List<ScoreCard> findByJudgeId(Long judgeId);
    List<ScoreCard> findByFightIdAndJudgeId(Integer fightId, Long judgeId);
}
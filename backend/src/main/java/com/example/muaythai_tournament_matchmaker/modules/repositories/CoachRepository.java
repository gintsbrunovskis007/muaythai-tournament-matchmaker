package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.tournament.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query("SELECT c FROM Coach c WHERE c.gym.id = :gymId")
    List<Coach> findByGymId(@Param("gymId") Long gymId);

    Optional<Coach> findByIdentityId(Long identityId);

    List<Coach> findByGymName(String gymName);
}
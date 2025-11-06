package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.fighter.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {
    Optional<Fighter> findByIdentityFirstNameAndIdentityLastName(String firstName, String lastName);
    
    @Query("SELECT f FROM Fighter f WHERE f.gym.id = :gymId")
    List<Fighter> findByGymId(@Param("gymId") Long gymId);
    
    @Query("SELECT f FROM Fighter f WHERE f.weightClass = :weightClass")
    List<Fighter> findByWeightClass(@Param("weightClass") String weightClass);
    
    List<Fighter> findByGymName(String gymName);
}
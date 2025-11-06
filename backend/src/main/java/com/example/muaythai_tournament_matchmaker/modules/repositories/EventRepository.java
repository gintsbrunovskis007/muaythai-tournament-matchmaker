package com.example.muaythai_tournament_matchmaker.modules.repositories;

import com.example.muaythai_tournament_matchmaker.models.tournament.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDate(LocalDate date);
    List<Event> findByLocation(String location);
    
    @Query("SELECT e FROM Event e WHERE e.date BETWEEN :startDate AND :endDate")
    List<Event> findEventsBetweenDates(@Param("startDate") LocalDate startDate, 
                                      @Param("endDate") LocalDate endDate);
    
    Optional<Event> findByName(String name);
}
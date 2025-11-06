package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.tournament.Event;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.EventDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", id));
        return convertToDTO(event);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", id));

        updateEntityFromDTO(existingEvent, eventDTO);
        Event updatedEvent = eventRepository.save(existingEvent);
        return convertToDTO(updatedEvent);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event", id);
        }
        eventRepository.deleteById(id);
    }

    private EventDTO convertToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setName(event.getName());
        dto.setDate(event.getDate());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());
        return dto;
    }

    private Event convertToEntity(EventDTO dto) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setLocation(dto.getLocation());
        event.setDescription(dto.getDescription());
        return event;
    }

    private void updateEntityFromDTO(Event event, EventDTO dto) {
        if (dto.getName() != null) event.setName(dto.getName());
        if (dto.getDate() != null) event.setDate(dto.getDate());
        if (dto.getLocation() != null) event.setLocation(dto.getLocation());
        if (dto.getDescription() != null) event.setDescription(dto.getDescription());
    }
}
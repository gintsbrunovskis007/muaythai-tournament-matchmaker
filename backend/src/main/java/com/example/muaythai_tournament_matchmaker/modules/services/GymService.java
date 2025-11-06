package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fighter.Fighter;
import com.example.muaythai_tournament_matchmaker.models.tournament.Coach;
import com.example.muaythai_tournament_matchmaker.models.tournament.Gym;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.FighterDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.GymDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.CoachRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.FighterRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private FighterRepository fighterRepository;

    @Autowired
    private CoachRepository coachRepository;

    public List<GymDTO> getAllGyms() {
        return gymRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GymDTO getGymById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gym", id));
        return convertToDTO(gym);
    }

    public GymDTO createGym(GymDTO gymDTO) {
        Gym gym = convertToEntity(gymDTO);
        Gym savedGym = gymRepository.save(gym);
        return convertToDTO(savedGym);
    }

    public void deleteGym(Long id) {
        if (!gymRepository.existsById(id)) {
            throw new ResourceNotFoundException("Gym", id);
        }
        gymRepository.deleteById(id);
    }

    private GymDTO convertToDTO(Gym gym) {
        GymDTO dto = new GymDTO();
        dto.setName(gym.getName());
        dto.setAddress(gym.getAddress());
        dto.setEstablished(gym.getEstablished());
        dto.setLogo(gym.getLogo());
        dto.setDescription(gym.getDescription());

        if (gym.getHeadCoach() != null) {
            dto.setHeadCoachID(gym.getHeadCoach().getId());
        }

        if (gym.getFighters() != null) {
            List<Long> fighterIDs = gym.getFighters().stream()
                    .map(Fighter::getId)
                    .collect(Collectors.toList());
            dto.setFighters(fighterIDs);
        } else {
            dto.setFighters(new ArrayList<>());
        }
            return dto;
    }

    private Gym convertToEntity(GymDTO dto) {
        Gym gym = new Gym();
        gym.setName(dto.getName());
        gym.setAddress(dto.getAddress());
        gym.setEstablished(dto.getEstablished());
        gym.setLogo(dto.getLogo());
        gym.setDescription(dto.getDescription());

        if (dto.getHeadCoachID() != null) {
            Coach headCoach = coachRepository.findById(dto.getHeadCoachID())
                    .orElseThrow(() -> new ResourceNotFoundException("Coach", dto.getHeadCoachID()));
            gym.setHeadCoach(headCoach);
        }

        if (dto.getFighters() != null && !dto.getFighters().isEmpty()) {
            List<Fighter> fighters = dto.getFighters().stream()
                    .map(id -> fighterRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Fighter", id)))
                    .collect(Collectors.toList());
            gym.setFighters(fighters);
        } else {
            gym.setFighters(new ArrayList<>());
        }

        return gym;
    }

}
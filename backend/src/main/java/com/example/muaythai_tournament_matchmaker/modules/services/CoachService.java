package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fighter.Identity;
import com.example.muaythai_tournament_matchmaker.models.tournament.Coach;
import com.example.muaythai_tournament_matchmaker.models.tournament.Gym;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.CoachDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.CoachRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private GymRepository gymRepository;

    public List<CoachDTO> getAllCoaches() {
        return coachRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CoachDTO getCoachById(Long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", id));
        return convertToDTO(coach);
    }

    public CoachDTO createCoach(CoachDTO coachDTO) {
        Coach coach = convertToEntity(coachDTO);
        Coach savedCoach = coachRepository.save(coach);
        return convertToDTO(savedCoach);
    }

    public CoachDTO updateCoach(Long id, CoachDTO coachDTO) {
        Coach existingCoach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", id));

        updateEntityFromDTO(existingCoach, coachDTO);
        Coach updatedCoach = coachRepository.save(existingCoach);
        return convertToDTO(updatedCoach);
    }

    public void deleteCoach(Long id) {
        if (!coachRepository.existsById(id)) {
            throw new ResourceNotFoundException("Coach", id);
        }
        coachRepository.deleteById(id);
    }

    public List<CoachDTO> getCoachesByGym(Long gymId) {
        return coachRepository.findByGymId(gymId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CoachDTO convertToDTO(Coach coach) {
        CoachDTO dto = new CoachDTO();

        if (coach.getIdentity() != null) {
            IdentityDTO identityDTO = new IdentityDTO();
            identityDTO.setFirstName(coach.getIdentity().getFirstName());
            identityDTO.setLastName(coach.getIdentity().getLastName());
            identityDTO.setDateOfBirth(coach.getIdentity().getDateOfBirth());
            identityDTO.setRole(coach.getIdentity().getRole());
            dto.setIdentity(identityDTO);
        }

        if (coach.getGym() != null) {
            dto.setGymID(coach.getGym().getId());
        }

        return dto;
    }

    private Coach convertToEntity(CoachDTO dto) {
        Coach coach = new Coach();

        if (dto.getIdentity() != null) {
            Identity identity = new Identity();
            identity.setFirstName(dto.getIdentity().getFirstName());
            identity.setLastName(dto.getIdentity().getLastName());
            identity.setDateOfBirth(dto.getIdentity().getDateOfBirth());
            identity.setRole(dto.getIdentity().getRole());
            coach.setIdentity(identity);
        }

        coach.setProfilePicture(dto.getProfilePicture());

        if (dto.getGymID() != null) {
            Gym gym = gymRepository.findById(dto.getGymID())
                    .orElseThrow(() -> new ResourceNotFoundException("Gym", dto.getGymID()));
            coach.setGym(gym);
        }

        return coach;
    }

    private void updateEntityFromDTO(Coach coach, CoachDTO dto) {
        if (dto.getIdentity() != null && coach.getIdentity() != null) {
            IdentityDTO identityDTO = dto.getIdentity();
            Identity identity = coach.getIdentity();
            if (identityDTO.getFirstName() != null) identity.setFirstName(identityDTO.getFirstName());
            if (identityDTO.getLastName() != null) identity.setLastName(identityDTO.getLastName());
            if (identityDTO.getDateOfBirth() != null) identity.setDateOfBirth(identityDTO.getDateOfBirth());
            if (identityDTO.getRole() != null) identity.setRole(identityDTO.getRole());
        }

        if (dto.getProfilePicture() != null) coach.setProfilePicture(dto.getProfilePicture());

        if (dto.getGymID() != null) {
            Gym gym = gymRepository.findById(dto.getGymID())
                    .orElseThrow(() -> new ResourceNotFoundException("Gym", dto.getGymID()));
            coach.setGym(gym);
        }
    }
}
package com.example.muaythai_tournament_matchmaker.modules.services;// FighterService.java

import com.example.muaythai_tournament_matchmaker.models.fighter.*;
import com.example.muaythai_tournament_matchmaker.models.fighter.Record;
import com.example.muaythai_tournament_matchmaker.models.tournament.Gym;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.FighterDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.FighterRepository;
import com.example.muaythai_tournament_matchmaker.modules.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FighterService {

    @Autowired
    private FighterRepository fighterRepository;

    @Autowired
    private GymRepository gymRepository;

    public List<FighterDTO> getAllFighters() {
        return fighterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FighterDTO getFighterById(Long id) {
        Fighter fighter = fighterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fighter", id));
        return convertToDTO(fighter);
    }

    public FighterDTO createFighter(FighterDTO fighterDTO) {
        Fighter fighter = convertToEntity(fighterDTO);
        Fighter savedFighter = fighterRepository.save(fighter);
        return convertToDTO(savedFighter);
    }

    public FighterDTO updateFighter(Long id, FighterDTO fighterDTO) {
        Fighter existingFighter = fighterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fighter", id));

        updateEntityFromDTO(existingFighter, fighterDTO);
        Fighter updatedFighter = fighterRepository.save(existingFighter);
        return convertToDTO(updatedFighter);
    }

    public void deleteFighter(Long id) {
        if (!fighterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fighter", id);
        }
        fighterRepository.deleteById(id);
    }

    private FighterDTO convertToDTO(Fighter fighter) {
        FighterDTO dto = new FighterDTO();

        if (fighter.getIdentity() != null) {
            IdentityDTO identityDTO = new IdentityDTO();
            identityDTO.setFirstName(fighter.getIdentity().getFirstName());
            identityDTO.setLastName(fighter.getIdentity().getLastName());
            identityDTO.setDateOfBirth(fighter.getIdentity().getDateOfBirth());
            identityDTO.setRole(fighter.getIdentity().getRole());
            dto.setIdentity(identityDTO);
        }

        if (fighter.getFighterImage() != null) {
            dto.setFullHeightImage(fighter.getFighterImage().getFullHeightImage());
            dto.setHeadToWaistImage(fighter.getFighterImage().getHeadToWaistImage());
            dto.setCloseUpImage(fighter.getFighterImage().getCloseUpImage());
        }

        if (fighter.getBody() != null) {
            dto.setWeight(fighter.getBody().getWeight());
            dto.setHeight(fighter.getBody().getHeight());
            dto.setWingspan(fighter.getBody().getWingspan());
            dto.setLegReach(fighter.getBody().getLeg_reach());
        }

        if (fighter.getFightRecord() != null) {
            dto.setWins(fighter.getFightRecord().getWins());
            dto.setLosses(fighter.getFightRecord().getLosses());
            dto.setDraws(fighter.getFightRecord().getDraws());
            dto.setNoContests(fighter.getFightRecord().getNoContests());
        }

        if (fighter.getGym() != null) {
            dto.setGymID((long) fighter.getGym().getId().intValue());
        }

        return dto;
    }

    private Fighter convertToEntity(FighterDTO dto) {
        Fighter fighter = new Fighter();

        if (dto.getIdentity() != null) {
            Identity identity = new Identity();
            identity.setFirstName(dto.getIdentity().getFirstName());
            identity.setLastName(dto.getIdentity().getLastName());
            identity.setDateOfBirth(dto.getIdentity().getDateOfBirth());
            identity.setRole(dto.getIdentity().getRole());
            fighter.setIdentity(identity);
        }

        Body body = new Body();
        body.setWeight(dto.getWeight());
        body.setHeight(dto.getHeight());
        body.setWingspan(dto.getWingspan());
        body.setLeg_reach(dto.getLegReach());
        fighter.setBody(body);

        Record record = new Record();
        record.setWins(dto.getWins());
        record.setLosses(dto.getLosses());
        record.setDraws(dto.getDraws());
        record.setNoContests(dto.getNoContests());
        fighter.setFightRecord(record);

        if (dto.getFullHeightImage() != null || dto.getHeadToWaistImage() != null || dto.getCloseUpImage() != null) {
            FighterImage fighterImage = new FighterImage();
            fighterImage.setFullHeightImage(dto.getFullHeightImage());
            fighterImage.setHeadToWaistImage(dto.getHeadToWaistImage());
            fighterImage.setCloseUpImage(dto.getCloseUpImage());
            fighter.setFighterImage(fighterImage);
        }

        if (dto.getGymID() != 0) {
            Gym gym = gymRepository.findById((long) dto.getGymID())
                    .orElseThrow(() -> new ResourceNotFoundException("Gym", dto.getGymID()));
            fighter.setGym(gym);
        }

        return fighter;
    }

    private void updateEntityFromDTO(Fighter fighter, FighterDTO dto) {

        if (dto.getIdentity() != null) {
            if (fighter.getIdentity() == null) {
                fighter.setIdentity(new Identity());
            }
            Identity identity = fighter.getIdentity();
            IdentityDTO identityDTO = dto.getIdentity();
            if (identityDTO.getFirstName() != null) identity.setFirstName(identityDTO.getFirstName());
            if (identityDTO.getLastName() != null) identity.setLastName(identityDTO.getLastName());
            if (identityDTO.getDateOfBirth() != null) identity.setDateOfBirth(identityDTO.getDateOfBirth());
            if (identityDTO.getRole() != null) identity.setRole(identityDTO.getRole());
        }

        if (dto.getWeight() != null) fighter.getBody().setWeight(dto.getWeight());
        if (dto.getHeight() != null) fighter.getBody().setHeight(dto.getHeight());
        if (dto.getWingspan() != null) fighter.getBody().setWingspan(dto.getWingspan());
        if (dto.getLegReach() != null) fighter.getBody().setLeg_reach(dto.getLegReach());

        fighter.getFightRecord().setWins(dto.getWins());
        fighter.getFightRecord().setLosses(dto.getLosses());
        fighter.getFightRecord().setDraws(dto.getDraws());
        fighter.getFightRecord().setNoContests(dto.getNoContests());

        if (dto.getGymID() != 0) {
            Gym gym = gymRepository.findById((long) dto.getGymID())
                    .orElseThrow(() -> new ResourceNotFoundException("Gym", dto.getGymID()));
            fighter.setGym(gym);
        }
    }
}
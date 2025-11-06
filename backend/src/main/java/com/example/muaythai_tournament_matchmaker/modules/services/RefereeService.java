package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.models.fighter.Identity;
import com.example.muaythai_tournament_matchmaker.models.tournament.Referee;
import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.RefereeDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.modules.repositories.RefereeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefereeService {

    @Autowired
    private RefereeRepository refereeRepository;

    public List<RefereeDTO> getAllReferees() {
        return refereeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RefereeDTO getRefereeById(Long id) {
        Referee referee = refereeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Referee", id));
        return convertToDTO(referee);
    }

    public RefereeDTO createReferee(RefereeDTO refereeDTO) {
        Referee referee = convertToEntity(refereeDTO);
        Referee savedReferee = refereeRepository.save(referee);
        return convertToDTO(savedReferee);
    }

    private RefereeDTO convertToDTO(Referee referee) {
        RefereeDTO dto = new RefereeDTO();

        if (referee.getIdentity() != null) {
            IdentityDTO identityDTO = new IdentityDTO();
            identityDTO.setFirstName(referee.getIdentity().getFirstName());
            identityDTO.setLastName(referee.getIdentity().getLastName());
            identityDTO.setDateOfBirth(referee.getIdentity().getDateOfBirth());
            identityDTO.setRole(referee.getIdentity().getRole());
            dto.setIdentity(identityDTO);
        }

        dto.setProfilePicture(referee.getProfilePicture());
        dto.setYearsOfExperience(referee.getYearsOfExperience());
        return dto;
    }

    private Referee convertToEntity(RefereeDTO dto) {
        Referee referee = new Referee();

        if (dto.getIdentity() != null) {
            Identity identity = new Identity();
            identity.setFirstName(dto.getIdentity().getFirstName());
            identity.setLastName(dto.getIdentity().getLastName());
            identity.setDateOfBirth(dto.getIdentity().getDateOfBirth());
            identity.setRole(dto.getIdentity().getRole());
            referee.setIdentity(identity);
        }

        referee.setProfilePicture(dto.getProfilePicture());
        referee.setYearsOfExperience(dto.getYearsOfExperience());

        return referee;
    }
}
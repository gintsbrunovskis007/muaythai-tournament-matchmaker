package com.example.muaythai_tournament_matchmaker.modules.services;

import com.example.muaythai_tournament_matchmaker.modules.dtos.fighter.IdentityDTO;
import com.example.muaythai_tournament_matchmaker.modules.dtos.tournament.JudgeDTO;
import com.example.muaythai_tournament_matchmaker.modules.exceptions.ResourceNotFoundException;
import com.example.muaythai_tournament_matchmaker.models.fighter.Identity;
import com.example.muaythai_tournament_matchmaker.models.tournament.Judge;
import com.example.muaythai_tournament_matchmaker.modules.repositories.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeService {

    @Autowired
    private JudgeRepository judgeRepository;

    public List<JudgeDTO> getAllJudges() {
        return judgeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JudgeDTO getJudgeById(Long id) {
        Judge judge = judgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Judge", id));
        return convertToDTO(judge);
    }

    public JudgeDTO createJudge(JudgeDTO judgeDTO) {
        Judge judge = convertToEntity(judgeDTO);
        Judge savedJudge = judgeRepository.save(judge);
        return convertToDTO(savedJudge);
    }

    private JudgeDTO convertToDTO(Judge judge) {
        JudgeDTO dto = new JudgeDTO();

        if (judge.getIdentity() != null) {
            IdentityDTO identityDTO = new IdentityDTO();
            identityDTO.setFirstName(judge.getIdentity().getFirstName());
            identityDTO.setLastName(judge.getIdentity().getLastName());
            identityDTO.setDateOfBirth(judge.getIdentity().getDateOfBirth());
            identityDTO.setRole(judge.getIdentity().getRole());
            dto.setIdentity(identityDTO);
        }

        dto.setProfilePicture(judge.getProfilePicture());
        dto.setYearsOfExperience(judge.getYearsOfExperience());
        return dto;
    }

    private Judge convertToEntity(JudgeDTO dto) {
        Judge judge = new Judge();

        if (dto.getIdentity() != null) {
            Identity identity = new Identity();
            identity.setFirstName(dto.getIdentity().getFirstName());
            identity.setLastName(dto.getIdentity().getLastName());
            identity.setDateOfBirth(dto.getIdentity().getDateOfBirth());
            identity.setRole(dto.getIdentity().getRole());
            judge.setIdentity(identity);
        }

        judge.setProfilePicture(dto.getProfilePicture());
        judge.setYearsOfExperience(dto.getYearsOfExperience());

        return judge;
    }
}
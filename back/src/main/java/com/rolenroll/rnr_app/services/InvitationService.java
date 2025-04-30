package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.dto.InvitationDTO;
import com.rolenroll.rnr_app.entities.Campaign;
import com.rolenroll.rnr_app.entities.Invitation;
import com.rolenroll.rnr_app.entities.InvitationStatus;
import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.mappers.InvitationMapper;
import com.rolenroll.rnr_app.repositories.CampaignRepository;
import com.rolenroll.rnr_app.repositories.InvitationRepository;
import com.rolenroll.rnr_app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final InvitationMapper mapper;
    private final UserRepository userRepository;
    private final CampaignRepository campaignRepository;

    public InvitationService(
            InvitationRepository invitationRepository,
            InvitationMapper mapper,
            UserRepository userRepository,
            CampaignRepository campaignRepository
    ) {
        this.invitationRepository = invitationRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.campaignRepository = campaignRepository;
    }

    public InvitationDTO createInvitation(InvitationDTO dto) {
        Invitation invitation = mapper.toEntity(dto);
        invitation.setStatus(InvitationStatus.SENT);
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setUpdatedAt(LocalDateTime.now());

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
        Campaign campaign = campaignRepository.findById(dto.campaignId())
                .orElseThrow(() -> new EntityNotFoundException("Campagne non trouvée"));

        invitation.setUser(user);
        invitation.setCampaign(campaign);

        if (dto.createdById() != null) {
            invitation.setCreatedBy(userRepository.findById(dto.createdById()).orElse(null));
        }
        if (dto.updatedById() != null) {
            invitation.setUpdatedBy(userRepository.findById(dto.updatedById()).orElse(null));
        }

        return mapper.toDto(invitationRepository.save(invitation));
    }

    public InvitationDTO acceptInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotFoundException("Invitation introuvable"));
        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitation.setUpdatedAt(LocalDateTime.now());
        return mapper.toDto(invitationRepository.save(invitation));
    }

    public InvitationDTO declineInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotFoundException("Invitation introuvable"));
        invitation.setStatus(InvitationStatus.DECLINED);
        invitation.setUpdatedAt(LocalDateTime.now());
        return mapper.toDto(invitationRepository.save(invitation));
    }
}

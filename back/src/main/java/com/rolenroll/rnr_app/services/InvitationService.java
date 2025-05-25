package com.rolenroll.rnr_app.services;
import org.springframework.security.core.context.SecurityContextHolder;

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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final InvitationMapper mapper;
    private final UserRepository userRepository;
    private final CampaignRepository campaignRepository;
    private final NotificationService notificationService;

    public InvitationService(
            InvitationRepository invitationRepository,
            InvitationMapper mapper,
            UserRepository userRepository,
            CampaignRepository campaignRepository,
            NotificationService notificationService
    ) {
        this.invitationRepository = invitationRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.campaignRepository = campaignRepository;
        this.notificationService = notificationService;
    }

    public InvitationDTO createInvitation(InvitationDTO dto) {
        User user = userRepository.findByName(dto.userName())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur '" + dto.userName() + "' introuvable"));

        Campaign campaign = campaignRepository.findById(dto.campaignId())
                .orElseThrow(() -> new EntityNotFoundException("Campagne ID " + dto.campaignId() + " introuvable"));

        // 🧙 Vérification : seul le MJ peut inviter
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User creator = userRepository.findByName(username)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur connecté introuvable"));

        if (!campaign.getCreatedBy().getId().equals(creator.getId())) {
            throw new SecurityException("Seul le maître du jeu peut inviter des joueurs à cette campagne.");
        }

        // ⛔ Vérification : invitation déjà envoyée ou acceptée
        boolean invitationExists = invitationRepository.findAll().stream()
                .anyMatch(inv -> inv.getUser().getId().equals(user.getId()) &&
                        inv.getCampaign().getId().equals(campaign.getId()) &&
                        (inv.getStatus() == InvitationStatus.SENT || inv.getStatus() == InvitationStatus.ACCEPTED));
        if (invitationExists) {
            throw new IllegalStateException("Une invitation existe déjà pour cet utilisateur dans cette campagne.");
        }

        if (user.getId().equals(creator.getId())) {
            throw new IllegalArgumentException("Vous ne pouvez pas vous inviter vous-même à une campagne.");
        }

        // ✅ Création de l'invitation
        Invitation invitation = new Invitation();
        invitation.setUser(user);
        invitation.setCampaign(campaign);
        invitation.setStatus(InvitationStatus.SENT);
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setUpdatedAt(LocalDateTime.now());
        invitation.setCreatedBy(creator);
        invitation.setUpdatedBy(creator);

        Invitation savedInvitation = invitationRepository.save(invitation);

        // 🔔 Notification au joueur invité
        notificationService.notifyUser(
            user,
            "Vous avez été invité à la campagne : " + campaign.getTitle(),
            "INVITATION",
            campaign.getId()
        );

        return mapper.toDto(savedInvitation);
    }

    public InvitationDTO acceptInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotFoundException("Invitation introuvable"));

        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitation.setUpdatedAt(LocalDateTime.now());

        Invitation savedInvitation = invitationRepository.save(invitation);

        // 🔔 Notification au MJ
        User mj = invitation.getCampaign().getCreatedBy();
        User player = invitation.getUser();
        notificationService.notifyUser(
            mj,
            player.getName() + " a accepté votre invitation à la campagne : " + invitation.getCampaign().getTitle(),
            "INVITATION_ACCEPTED",
            invitation.getCampaign().getId()
        );

        return mapper.toDto(savedInvitation);
    }

    public InvitationDTO declineInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotFoundException("Invitation introuvable"));
        invitation.setStatus(InvitationStatus.DECLINED);
        invitation.setUpdatedAt(LocalDateTime.now());

        Invitation savedInvitation = invitationRepository.save(invitation);

        // 🔔 Notification au MJ
        User mj = invitation.getCampaign().getCreatedBy();
        User player = invitation.getUser();
        notificationService.notifyUser(
            mj,
            player.getName() + " a refusé votre invitation à la campagne : " + invitation.getCampaign().getTitle(),
            "INVITATION_DECLINED",
            invitation.getCampaign().getId()
        );

        return mapper.toDto(savedInvitation);
    }

    public List<InvitationDTO> getInvitationsByCampaign(Long campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campagne ID " + campaignId + " introuvable"));

        List<Invitation> invitations = invitationRepository.findByCampaign(campaign);
        return invitations.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}

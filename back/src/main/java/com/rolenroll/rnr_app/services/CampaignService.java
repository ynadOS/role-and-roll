package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.dto.CampaignDTO;
import com.rolenroll.rnr_app.entities.Campaign;
import com.rolenroll.rnr_app.entities.Universe;
import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.mappers.CampaignMapper;
import com.rolenroll.rnr_app.repositories.CampaignRepository;
import com.rolenroll.rnr_app.repositories.UniverseRepository;
import com.rolenroll.rnr_app.services.UserService;
import java.time.LocalDateTime;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;
    private final UserService userService;
    private final UniverseRepository universeRepository;

    public CampaignService(
            CampaignRepository campaignRepository,
            CampaignMapper campaignMapper,
            UserService userService,
            UniverseRepository universeRepository
    ) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
        this.userService = userService;
        this.universeRepository = universeRepository;
    }

    public List<CampaignDTO> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .map(campaignMapper::toDTO)
                .toList();
    }

    public CampaignDTO createCampaign(CampaignDTO dto, UserDetails userDetails) {
        User currentUser = userService.findByName(userDetails.getUsername());

        Campaign campaign = new Campaign();
        campaign.setTitle(dto.title());
        campaign.setDescription(dto.description());
        campaign.setCreatedBy(currentUser);
        campaign.setUpdatedBy(currentUser);
        campaign.setCreatedAt(LocalDateTime.now());
        campaign.setUpdatedAt(LocalDateTime.now());
        campaign.setStatus(dto.status());

        if (dto.universeId() != null) {
            Universe universe = universeRepository.findById(dto.universeId())
                    .orElseThrow(() -> new EntityNotFoundException("Univers introuvable"));
            campaign.setUniverse(universe);
        } else {
            campaign.setUniverse(null);
        }

        campaign.setUser(currentUser);

        return campaignMapper.toDTO(campaignRepository.save(campaign));
    }


    public CampaignDTO getCampaignById(Long id, UserDetails userDetails) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campagne introuvable"));

        User currentUser = userService.findByName(userDetails.getUsername());

        boolean isOwner = campaign.getCreatedBy().getName().equals(currentUser.getName());
        boolean isInvitedPlayer = campaign.getInvitations().stream()
            .anyMatch(invite -> invite.getUser().getId().equals(currentUser.getId())
                && invite.getStatus().name().equals("ACCEPTED"));

        if (!isOwner && !isInvitedPlayer) {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à consulter cette campagne");
        }

        return campaignMapper.toDTO(campaign);
    }

    public CampaignDTO updateCampaign(Long id, CampaignDTO dto, UserDetails userDetails) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campagne introuvable"));

        if (!campaign.getUser().getEmail().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à modifier cette campagne");
        }

        campaign.setTitle(dto.title());
        campaign.setDescription(dto.description());

        if (dto.universeId() != null) {
            Universe universe = universeRepository.findById(dto.universeId())
                    .orElseThrow(() -> new EntityNotFoundException("Univers introuvable"));
            campaign.setUniverse(universe);
        } else {
            campaign.setUniverse(null);
        }

        campaign.setStatus(dto.status());
        campaign.setUpdatedBy(userService.getCurrentUser());
        campaign.setUpdatedAt(LocalDateTime.now());

        return campaignMapper.toDTO(campaignRepository.save(campaign));
    }

    public void deleteCampaign(Long id, UserDetails userDetails) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campagne introuvable"));

        if (!campaign.getUser().getEmail().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer cette campagne");
        }

        campaignRepository.deleteById(id);
    }

    public List<CampaignDTO> getCampaignsByCurrentUser(UserDetails userDetails) {
        User currentUser = userService.findByName(userDetails.getUsername());
        return campaignRepository.findByUserId(currentUser.getId()).stream()
                .map(campaignMapper::toDTO)
                .toList();
    }
}

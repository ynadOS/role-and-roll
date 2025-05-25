package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.dto.NotificationDTO;
import com.rolenroll.rnr_app.entities.Notification;
import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.mappers.NotificationMapper;
import com.rolenroll.rnr_app.repositories.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.rolenroll.rnr_app.entities.Campaign;
import com.rolenroll.rnr_app.entities.Invitation;
import com.rolenroll.rnr_app.entities.InvitationStatus;
import java.util.Set;
import java.util.HashSet;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository,
                               NotificationMapper notificationMapper,
                               SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyUser(User user, String message, String type, Long campaignId) {
        Notification notification = new Notification(user, message, type, campaignId);
        notification.setCreatedBy(user);
        notificationRepository.save(notification);

        NotificationDTO dto = notificationMapper.toDto(notification);
        messagingTemplate.convertAndSendToUser(user.getName(), "/queue/notifications", dto);
    }

    public List<NotificationDTO> getNotificationsForUser(User user) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId()).stream()
            .map(notificationMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<NotificationDTO> getUnreadNotificationsForUser(User user) {
        return notificationRepository.findByUserIdAndReadFalseOrderByCreatedAtDesc(user.getId()).stream()
            .map(notificationMapper::toDto)
            .collect(Collectors.toList());
    }

    public void markAsRead(Long notificationId, User user) {
        Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("Notification introuvable"));
        if (!notification.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Vous n'avez pas l'autorisation de modifier cette notification");
        }
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public void notifyCampaignParticipants(Campaign campaign, String message, String type) {
        Set<User> destinataires = new HashSet<>();
        destinataires.add(campaign.getCreatedBy());

        if (campaign.getInvitations() != null) {
            for (Invitation invitation : campaign.getInvitations()) {
                if (invitation.getStatus() == InvitationStatus.ACCEPTED) {
                    destinataires.add(invitation.getUser());
                }
            }
        }

        for (User user : destinataires) {
            notifyUser(user, message, type, campaign.getId());
        }
    }
}
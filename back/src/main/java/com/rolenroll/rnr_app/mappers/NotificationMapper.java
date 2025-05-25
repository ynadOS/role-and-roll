package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.NotificationDTO;
import com.rolenroll.rnr_app.entities.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDTO toDto(Notification notification) {
        return new NotificationDTO(
            notification.getId(),
            notification.getMessage(),
            notification.getType(),
            notification.getRelatedCampaignId(),
            notification.isRead(),
            notification.getCreatedAt(),
            notification.getCreatedBy() != null ? notification.getCreatedBy().getName() : null
        );
    }
}

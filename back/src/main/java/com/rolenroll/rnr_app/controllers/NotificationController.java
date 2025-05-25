package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.NotificationDTO;
import com.rolenroll.rnr_app.services.NotificationService;
import com.rolenroll.rnr_app.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping
    public List<NotificationDTO> getMyNotifications(@AuthenticationPrincipal UserDetails userDetails) {
        var user = userService.findByName(userDetails.getUsername());
        return notificationService.getNotificationsForUser(user);
    }

    @GetMapping("/unread")
    public List<NotificationDTO> getUnreadNotifications(@AuthenticationPrincipal UserDetails userDetails) {
        var user = userService.findByName(userDetails.getUsername());
        return notificationService.getUnreadNotificationsForUser(user);
    }

    @PatchMapping("/{id}/read")
    public void markAsRead(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        var user = userService.findByName(userDetails.getUsername());
        notificationService.markAsRead(id, user);
    }
}

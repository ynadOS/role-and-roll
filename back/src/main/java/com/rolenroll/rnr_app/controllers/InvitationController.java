package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.InvitationDTO;
import com.rolenroll.rnr_app.services.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping
    public ResponseEntity<InvitationDTO> createInvitation(@RequestBody InvitationDTO dto) {
        InvitationDTO created = invitationService.createInvitation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<InvitationDTO> acceptInvitation(@PathVariable Long id) {
        return ResponseEntity.ok(invitationService.acceptInvitation(id));
    }

    @PatchMapping("/{id}/decline")
    public ResponseEntity<InvitationDTO> declineInvitation(@PathVariable Long id) {
        return ResponseEntity.ok(invitationService.declineInvitation(id));
    }
}

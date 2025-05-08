package com.rolenroll.rnr_app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.services.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@Tag(name = "Universes", description = "Endpoints    for managing universes")
@RestController
@RequestMapping("/api/universes")
public class UniverseController {

    private final UniverseService universeService;

    public UniverseController(UniverseService universeService) {
        this.universeService = universeService;
    }

    @Operation(summary = "Get all universes")
    @GetMapping
    public List<UniverseDTO> getAllUniverses() {
        return universeService.getAllUniverses();
    }

    @Operation(summary = "Get a universe by ID")
    @GetMapping("/{id}")
    public UniverseDTO getUniverseById(@PathVariable Long id) {
        return universeService.getUniverseById(id).orElse(null);
    }

    @Operation(summary = "Create a new universe")
    @PostMapping
    public UniverseDTO createUniverse(@RequestBody UniverseDTO universeDTO, @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        return universeService.createUniverse(universeDTO, userDetails);
    }

    @Operation(summary = "Update an existing universe")
    @PutMapping("/{id}")
    public UniverseDTO updateUniverse(@PathVariable Long id, @RequestBody UniverseDTO universeDTO) {
        return universeService.updateUniverse(id, universeDTO).orElse(null);
    }

    @Operation(summary = "Delete a universe")
    @DeleteMapping("/{id}")
    public void deleteUniverse(@PathVariable Long id) {
        universeService.deleteUniverse(id);
    }

    @Operation(summary = "Get universes of the current user", description = "Returns all universes created by the authenticated user")
    @GetMapping("/me")
    public List<UniverseDTO> getMyUniverses(@AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        return universeService.getUniversesByCurrentUser(userDetails);
    }

    @Operation(summary = "Duplicate a universe", description = "Creates a new universe by duplicating an existing one")
    @PostMapping("/{id}/duplicate")
    public UniverseDTO duplicateUniverse(@PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        return universeService.duplicateUniverse(id, userDetails);
    }
}

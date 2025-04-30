package com.rolenroll.rnr_app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.services.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Universes", description = "Endpoints    for managing universes")
@RestController
@RequestMapping("/api/universes")
public class UniverseController {

    @Autowired
    private UniverseService universeService;

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
    public UniverseDTO createUniverse(@RequestBody UniverseDTO universeDTO) {
        return universeService.createUniverse(universeDTO);
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
}

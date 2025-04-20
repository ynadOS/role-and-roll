package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.services.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universes")
public class UniverseController {

    @Autowired
    private UniverseService universeService;

    @GetMapping
    public List<UniverseDTO> getAllUniverses() {
        return universeService.getAllUniverses();
    }

    @GetMapping("/{id}")
    public UniverseDTO getUniverseById(@PathVariable Long id) {
        return universeService.getUniverseById(id).orElse(null);
    }

    @PostMapping
    public UniverseDTO createUniverse(@RequestBody UniverseDTO universeDTO) {
        return universeService.createUniverse(universeDTO);
    }

    @PutMapping("/{id}")
    public UniverseDTO updateUniverse(@PathVariable Long id, @RequestBody UniverseDTO universeDTO) {
        return universeService.updateUniverse(id, universeDTO).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUniverse(@PathVariable Long id) {
        universeService.deleteUniverse(id);
    }
}

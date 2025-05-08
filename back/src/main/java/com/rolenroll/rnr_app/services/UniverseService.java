package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.entities.Universe;
import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.mappers.UniverseMapper;
import com.rolenroll.rnr_app.repositories.UniverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniverseService {

    private final UniverseRepository universeRepository;
    private final UserService userService;
    private final UniverseMapper universeMapper;

    public UniverseService(UniverseRepository universeRepository, UserService userService, UniverseMapper universeMapper) {
        this.universeRepository = universeRepository;
        this.userService = userService;
        this.universeMapper = universeMapper;
    }

    public List<UniverseDTO> getAllUniverses() {
        return universeRepository.findAll().stream()
                .map(universeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UniverseDTO> getUniverseById(Long id) {
        return universeRepository.findById(id)
                .map(universeMapper::toDTO);
    }

    public UniverseDTO createUniverse(UniverseDTO universeDTO, UserDetails userDetails) {
        Universe universe = universeMapper.toEntity(universeDTO);
        User currentUser = userService.findByEmail(userDetails.getUsername());

        universe.setCreatedBy(currentUser);
        universe.setUpdatedBy(currentUser);
        universe.setCreatedAt(java.time.LocalDateTime.now());
        universe.setUpdatedAt(java.time.LocalDateTime.now());

        Universe saved = universeRepository.save(universe);
        return universeMapper.toDTO(saved);
    }

    public Optional<UniverseDTO> updateUniverse(Long id, UniverseDTO universeDTO) {
        return universeRepository.findById(id)
                .map(existing -> {
                    existing.setName(universeDTO.name());
                    existing.setDescription(universeDTO.description());
                    existing.setRules(universeDTO.rules());
                    Universe updated = universeRepository.save(existing);
                    return universeMapper.toDTO(updated);
                });
    }

    public void deleteUniverse(Long id) {
        universeRepository.deleteById(id);
    }

    public List<UniverseDTO> getUniversesByCurrentUser(UserDetails userDetails) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        return universeRepository.findByCreatedById(currentUser.getId()).stream()
                .map(universeMapper::toDTO)
                .toList();
    }

    public UniverseDTO duplicateUniverse(Long id, UserDetails userDetails) {
        Universe original = universeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Univers non trouvé"));

        User currentUser = userService.findByEmail(userDetails.getUsername());

        Universe copy = new Universe();
        String baseName = original.getName();
        String newName = baseName;
        int count = 1;
        while (universeRepository.existsByName(newName)) {
            newName = baseName + " (copie " + count++ + ")";
        }
        copy.setName(newName);
        copy.setDescription(original.getDescription());
        copy.setRules(original.getRules());
        copy.setCreatedBy(currentUser);
        copy.setUpdatedBy(currentUser);
        copy.setCreatedAt(java.time.LocalDateTime.now());
        copy.setUpdatedAt(java.time.LocalDateTime.now());

        Universe saved = universeRepository.save(copy);
        return universeMapper.toDTO(saved);
    }
}

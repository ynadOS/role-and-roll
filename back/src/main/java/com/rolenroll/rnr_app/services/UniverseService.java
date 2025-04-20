package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.entities.Universe;
import com.rolenroll.rnr_app.mappers.UniverseMapper;
import com.rolenroll.rnr_app.repositories.UniverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniverseService {

    @Autowired
    private UniverseRepository universeRepository;

    public List<UniverseDTO> getAllUniverses() {
        return universeRepository.findAll().stream()
                .map(UniverseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UniverseDTO> getUniverseById(Long id) {
        return universeRepository.findById(id)
                .map(UniverseMapper::toDTO);
    }

    public UniverseDTO createUniverse(UniverseDTO universeDTO) {
        Universe universe = UniverseMapper.toEntity(universeDTO);
        Universe saved = universeRepository.save(universe);
        return UniverseMapper.toDTO(saved);
    }

    public Optional<UniverseDTO> updateUniverse(Long id, UniverseDTO universeDTO) {
        return universeRepository.findById(id)
                .map(existing -> {
                    existing.setName(universeDTO.name());
                    existing.setDescription(universeDTO.description());
                    Universe updated = universeRepository.save(existing);
                    return UniverseMapper.toDTO(updated);
                });
    }

    public void deleteUniverse(Long id) {
        universeRepository.deleteById(id);
    }
}

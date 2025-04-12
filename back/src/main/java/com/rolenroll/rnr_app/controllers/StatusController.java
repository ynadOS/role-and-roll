package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.entities.Status;
import com.rolenroll.rnr_app.repositories.StatusRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}

package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.CampaignDTO;
import com.rolenroll.rnr_app.services.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<CampaignDTO> getAll() {
        return campaignService.getAllCampaigns();
    }

    @PostMapping
    public ResponseEntity<CampaignDTO> create(@RequestBody CampaignDTO dto) {
        return ResponseEntity.ok(campaignService.createCampaign(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(campaignService.getCampaignById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignDTO> update(@PathVariable Long id, @RequestBody CampaignDTO dto) {
        return ResponseEntity.ok(campaignService.updateCampaign(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}

package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.CampaignDTO;
import com.rolenroll.rnr_app.services.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@Tag(name = "Campaigns", description = "Endpoints for managing RPG campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Operation(summary = "Get all campaigns", description = "Returns a list of all RPG campaigns")
    @ApiResponse(responseCode = "200", description = "List of campaigns returned successfully")
    @GetMapping
    public List<CampaignDTO> getAll() {
        return campaignService.getAllCampaigns();
    }

    @Operation(summary = "Create a new campaign", description = "Creates and returns a new campaign")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Campaign created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<CampaignDTO> create(@RequestBody CampaignDTO dto) {
        return ResponseEntity.ok(campaignService.createCampaign(dto));
    }

    @Operation(summary = "Get campaign by ID", description = "Returns a campaign by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Campaign found"),
            @ApiResponse(responseCode = "404", description = "Campaign not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(campaignService.getCampaignById(id));
    }

    @Operation(summary = "Update campaign", description = "Updates an existing campaign")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Campaign updated successfully"),
            @ApiResponse(responseCode = "404", description = "Campaign not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CampaignDTO> update(@PathVariable Long id, @RequestBody CampaignDTO dto) {
        return ResponseEntity.ok(campaignService.updateCampaign(id, dto));
    }

    @Operation(summary = "Delete campaign", description = "Deletes a campaign by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Campaign deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Campaign not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}

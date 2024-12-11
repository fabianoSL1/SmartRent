package com.ufrrj.smartrent.rent.controller;

import com.ufrrj.smartrent.common.security.AuthUtils;
import com.ufrrj.smartrent.rent.dtos.CreateProposalRequest;
import com.ufrrj.smartrent.rent.dtos.ProposalResponse;
import com.ufrrj.smartrent.rent.model.Proposal;
import com.ufrrj.smartrent.rent.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("proposals")
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponse> createProposal(@RequestBody CreateProposalRequest request) {
        var username = AuthUtils.getCurrentAuthUsername();
        var proposal = proposalService.createProposal(username, request.getVehicleId());

        return ResponseEntity.status(201).body(createProposalResponse(proposal));
    }

    @PatchMapping("/{proposalId}/approve")
    public ResponseEntity<ProposalResponse> approveProposal(@PathVariable long proposalId) {
        var username = AuthUtils.getCurrentAuthUsername();
        var proposal = proposalService.approveProposal(username, proposalId);

        return ResponseEntity.ok(createProposalResponse(proposal));
    }

    @PatchMapping("/{proposalId}/reject")
    public ResponseEntity<ProposalResponse> rejectProposal(@PathVariable long proposalId) {
        var username = AuthUtils.getCurrentAuthUsername();
        var proposal = proposalService.rejectProposal(username, proposalId);

        return ResponseEntity.ok(createProposalResponse(proposal));
    }

    @PatchMapping("/{proposalId}/cancel")
    public ResponseEntity<ProposalResponse> cancelProposal(@PathVariable long proposalId) {
        var username = AuthUtils.getCurrentAuthUsername();
        var proposal = proposalService.cancelProposal(username, proposalId);

        return ResponseEntity.ok(createProposalResponse(proposal));
    }

    private ProposalResponse createProposalResponse(Proposal proposal) {
        return ProposalResponse.builder()
                .id(proposal.getId())
                .status(proposal.getStatus())
                .renterId(proposal.getRenter().getId())
                .vehicleId(proposal.getVehicle().getId())
                .ownerId(proposal.getVehicle().getOwner().getId())
                .build();
    }
}

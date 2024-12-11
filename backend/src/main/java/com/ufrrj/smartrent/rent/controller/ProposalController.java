package com.ufrrj.smartrent.rent.controller;

import com.ufrrj.smartrent.rent.dtos.CreateProposalRequest;
import com.ufrrj.smartrent.rent.dtos.ProposalResponse;
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
        var proposal = proposalService.createProposal(request.getVehicleId());
        return ResponseEntity.status(201).body(new ProposalResponse(proposal));
    }

    @PatchMapping("/{proposalId}/approve")
    public ResponseEntity<ProposalResponse> approveProposal(@PathVariable long proposalId) {
        var proposal = proposalService.approveProposal(proposalId);
        return ResponseEntity.ok(new ProposalResponse(proposal));
    }

    @PatchMapping("/{proposalId}/reject")
    public ResponseEntity<ProposalResponse> rejectProposal(@PathVariable long proposalId) {
        var proposal = proposalService.rejectProposal(proposalId);
        return ResponseEntity.ok(new ProposalResponse(proposal));
    }

    @PatchMapping("/{proposalId}/cancel")
    public ResponseEntity<ProposalResponse> cancelProposal(@PathVariable long proposalId) {
        var proposal = proposalService.cancelProposal(proposalId);
        return ResponseEntity.ok(new ProposalResponse(proposal));
    }
}

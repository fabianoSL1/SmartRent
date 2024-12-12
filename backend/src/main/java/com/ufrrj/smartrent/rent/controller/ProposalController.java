package com.ufrrj.smartrent.rent.controller;

import com.ufrrj.smartrent.rent.dtos.CreateProposalRequest;
import com.ufrrj.smartrent.rent.dtos.ProposalResponse;
import com.ufrrj.smartrent.rent.dtos.RentResponse;
import com.ufrrj.smartrent.rent.service.ProposalService;
import com.ufrrj.smartrent.rent.service.RentService;
import com.ufrrj.smartrent.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("proposals")
public class ProposalController {

    private final ProposalService proposalService;

    private final RentService rentService;

    @GetMapping("/list/receive")
    public ResponseEntity<List<ProposalResponse>> getProposalByOwner() {
        var proposals = proposalService.getProposalByOwner()
                .stream()
                .map(ProposalResponse::new)
                .toList();

        return ResponseEntity.ok(proposals);
    }

    @GetMapping("/list/send")
    public ResponseEntity<List<ProposalResponse>> getProposalByRenter() {
        var proposals = proposalService.getProposalByRenter()
                .stream()
                .map(ProposalResponse::new)
                .toList();

        return ResponseEntity.ok(proposals);
    }

    @PostMapping
    public ResponseEntity<ProposalResponse> createProposal(@RequestBody CreateProposalRequest request) {
        var proposal = proposalService.createProposal(request);
        return ResponseEntity.status(201).body(new ProposalResponse(proposal));
    }

    @PostMapping("/{proposalId}/rent")
    public ResponseEntity<RentResponse> createRent(@PathVariable long proposalId) {
        var proposal = proposalService.getProposalById(proposalId);
        var rent = rentService.createRent(proposal);

        return ResponseEntity.status(201).body(new RentResponse(rent));
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

package com.ufrrj.smartrent.rent.service;

import com.ufrrj.smartrent.rent.model.Proposal;
import com.ufrrj.smartrent.rent.repository.ProposalRepository;
import com.ufrrj.smartrent.user.service.OwnerService;
import com.ufrrj.smartrent.user.service.RenterService;
import com.ufrrj.smartrent.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    private final VehicleService vehicleService;

    private final RenterService renterService;

    private final OwnerService ownerService;


    public Proposal createProposal(String username, long vehicleId) {
        var vehicle = vehicleService.getVehicleById(vehicleId);

        var renter = renterService.findByUsername(username);

        var proposal = renter.createProposal(vehicle);

        return proposalRepository.save(proposal);
    }


    public Proposal cancelProposal(String username, long proposalId) {
        var proposal = getProposal(proposalId);

        var renter = renterService.findByUsername(username);

        renter.cancelProposal(proposal);

        return proposalRepository.save(proposal);
    }


    public Proposal approveProposal(String username, long proposalId) {
        var proposal = getProposal(proposalId);

        var owner = ownerService.findOwnerByUsername(username);

        owner.approveProposal(proposal);

        return proposalRepository.save(proposal);
    }

    public Proposal rejectProposal(String username, long proposalId) {
        var proposal = getProposal(proposalId);

        var owner = ownerService.findOwnerByUsername(username);

        owner.rejectProposal(proposal);

        return proposalRepository.save(proposal);
    }

    private Proposal getProposal(long proposalId) {
        return proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal with id " + proposalId + " not found"));
    }
}

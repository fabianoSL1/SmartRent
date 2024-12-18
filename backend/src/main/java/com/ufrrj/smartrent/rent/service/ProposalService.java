package com.ufrrj.smartrent.rent.service;

import com.ufrrj.smartrent.common.exception.DomainException;
import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.rent.dtos.CreateProposalRequest;
import com.ufrrj.smartrent.rent.model.Proposal;
import com.ufrrj.smartrent.rent.repository.ProposalRepository;
import com.ufrrj.smartrent.user.service.OwnerService;
import com.ufrrj.smartrent.user.service.RenterService;
import com.ufrrj.smartrent.vehicle.enums.VehicleStatus;
import com.ufrrj.smartrent.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    private final VehicleService vehicleService;

    private final RenterService renterService;

    private final OwnerService ownerService;

    public Proposal getProposalById(Long proposalId) {
        return proposalRepository.findById(proposalId).orElseThrow(() -> new DomainException("Proposta não encontada"));
    }
    public List<Proposal> getProposalByOwner() {
        var owner = ownerService.getCurrentOwner();
        return proposalRepository.getProposalByVehicleOwnerId(owner.getId());
    }

    public List<Proposal> getProposalByRenter() {
        var renter = renterService.getCurrentRenter();
        return proposalRepository.getProposalByRenterId(renter.getId());
    }


    public Proposal createProposal(CreateProposalRequest request) {
        var vehicle = vehicleService.getVehicleById(request.getVehicleId());

        var renter = renterService.getCurrentRenter();

        var proposal = renter.createProposal(vehicle, request);

        return proposalRepository.save(proposal);
    }

    public Proposal cancelProposal(long proposalId) {
        var proposal = getProposal(proposalId);

        var renter = renterService.getCurrentRenter();

        renter.cancelProposal(proposal);

        return proposalRepository.save(proposal);
    }

    public Proposal approveProposal(long proposalId) {
        var proposal = getProposal(proposalId);

        var owner = ownerService.getCurrentOwner();

        owner.approveProposal(proposal);

        return proposalRepository.save(proposal);
    }

    public Proposal rejectProposal(long proposalId) {
        var proposal = getProposal(proposalId);

        var owner = ownerService.getCurrentOwner();

        owner.rejectProposal(proposal);

        return proposalRepository.save(proposal);
    }

    private Proposal getProposal(long proposalId) {
        return proposalRepository.findById(proposalId)
                .orElseThrow(() -> new NotFoundException("Proposta não encontrada"));
    }
}

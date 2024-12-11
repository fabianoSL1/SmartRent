package com.ufrrj.smartrent.rent.dtos;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.rent.model.Proposal;

import lombok.Data;

@Data
public class ProposalResponse {

    private long id;

    private long renterId;

    private long vehicleId;

    private int amount;

    private ProposalStatus status;


    public ProposalResponse(Proposal proposal) {
        this.id = proposal.getId();
        this.renterId = proposal.getRenter().getId();
        this.vehicleId = proposal.getVehicle().getId();
        this.amount = proposal.getAmount();
        this.status = proposal.getStatus();
    }
}

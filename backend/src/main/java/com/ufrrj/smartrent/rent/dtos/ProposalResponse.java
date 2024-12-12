package com.ufrrj.smartrent.rent.dtos;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.rent.model.Proposal;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProposalResponse {

    private long id;

    private long renterId;

    private String renterName;

    private long vehicleId;

    private String vehicleIdentifier;

    private int amount;

    private ProposalStatus status;

    private LocalDate beginDate;

    private LocalDate endDate;


    public ProposalResponse(Proposal proposal) {
        this.id = proposal.getId();
        this.renterId = proposal.getRenter().getId();
        this.vehicleId = proposal.getVehicle().getId();
        this.amount = proposal.getAmount();
        this.status = proposal.getStatus();
        this.renterName = proposal.getRenter().getUser().getName();
        this.vehicleIdentifier = proposal.getVehicle().getIdentifier();
        this.beginDate = proposal.getBeginDate();
        this.endDate = proposal.getEndDate();
    }
}

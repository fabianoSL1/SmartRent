package com.ufrrj.smartrent.models;

import com.ufrrj.smartrent.enums.ProposalStatus;
import jakarta.persistence.Entity;

@Entity
public class Renter extends User {

    public Proposal createProposal(Vehicle vehicle) {
        if (!vehicle.isAvailable()) {
            throw new IllegalArgumentException("Vehicle is not available");
        }

        return Proposal.builder()
                .vehicle(vehicle)
                .renter(this)
                .build();
    }

    public void cancelProposal(Proposal proposal) {
        if (proposal.getRenter().getId() != this.getId()) {
            throw new IllegalArgumentException("is not renter");
        }

        proposal.setStatus(ProposalStatus.CANCELLED);
    }

}

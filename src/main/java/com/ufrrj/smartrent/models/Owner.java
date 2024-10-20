package com.ufrrj.smartrent.models;

import com.ufrrj.smartrent.enums.ProposalStatus;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Entity()
public class Owner extends User {

    public void approveProposal(Proposal proposal) {
        verifyProposal(proposal);
        proposal.setStatus(ProposalStatus.APPROVED);
    }


    public void rejectProposal(Proposal proposal) {
        verifyProposal(proposal);
        proposal.setStatus(ProposalStatus.REJECTED);
    }


    private void verifyProposal(Proposal proposal) {
        if (proposal.getStatus() != ProposalStatus.PENDING) {
            throw new RuntimeException("proposal is not in pending");
        }

        if (proposal.getVehicle().getOwner().getId() != this.getId()) {
            throw new RuntimeException("is not owner of vehicle");
        }
    }
}

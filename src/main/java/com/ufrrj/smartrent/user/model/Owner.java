package com.ufrrj.smartrent.user.model;

import com.ufrrj.smartrent.common.exception.DomainException;
import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.rent.model.Proposal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    private User user;


    public void approveProposal(Proposal proposal) {
        changeStatus(proposal, ProposalStatus.APPROVED);
        proposal.getVehicle().setAvailable(false);
    }

    public void rejectProposal(Proposal proposal) {
        changeStatus(proposal, ProposalStatus.REJECTED);
        proposal.getVehicle().setAvailable(true);
    }

    private void changeStatus(Proposal proposal, ProposalStatus status) {
        if (proposal.getVehicle().getOwner().getId() != this.id) {
            throw new DomainException("Only owner");
        }

        if (!proposal.getStatus().equals(ProposalStatus.PENDING)) {
            throw new DomainException("Proposal is not pending");
        }

        proposal.setStatus(status);
    }

}

package com.ufrrj.smartrent.user.model;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.rent.model.Proposal;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    List<Vehicle> vehicles;


    public void approveProposal(Proposal proposal) {
        verify(proposal, ProposalStatus.APPROVED);
    }

    public void rejectProposal(Proposal proposal) {
        verify(proposal, ProposalStatus.REJECTED);
    }

    private static void verify(Proposal proposal, ProposalStatus status) {
        if (!proposal.getStatus().equals(ProposalStatus.PENDING)) {
            throw new RuntimeException("Proposal is not pending");
        }

        proposal.setStatus(status);
    }


}

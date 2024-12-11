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
        proposal.getVehicle().turnReserved();
    }

    public void rejectProposal(Proposal proposal) {
        changeStatus(proposal, ProposalStatus.REJECTED);
    }

    private void changeStatus(Proposal proposal, ProposalStatus status) {
        if (proposal.getVehicle().getOwner().getId() != this.id) {
            throw new DomainException("Apenas o proprietario do veiculo pode realizar esta ação.");
        }

        if (!proposal.getStatus().equals(ProposalStatus.PENDING)) {
            throw new DomainException("Proposta não está pendente.");
        }

        proposal.setStatus(status);
    }

}

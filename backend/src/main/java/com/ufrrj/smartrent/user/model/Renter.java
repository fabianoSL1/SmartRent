package com.ufrrj.smartrent.user.model;

import com.ufrrj.smartrent.common.exception.DomainException;
import com.ufrrj.smartrent.rent.dtos.CreateProposalRequest;
import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.rent.model.Proposal;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
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
@Table(name = "renters")
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    private User user;

    public Proposal createProposal(Vehicle vehicle, CreateProposalRequest request) {
        if (vehicle.getOwner().getUser().getId() == this.user.getId()) {
            throw new DomainException("Proprietarios não podem alugar seus veiculos.");
        }

        if (!vehicle.isAvailable()) {
            throw new DomainException("Veiculo não está disponivel.");
        }

        if (request.getBeginDate().isAfter(request.getEndDate())) {
            throw new DomainException("A data de inicio não pode ser depois da data final.");
        }

        return Proposal.builder()
                .renter(this)
                .amount(request.getAmount())
                .vehicle(vehicle)
                .status(ProposalStatus.PENDING)
                .beginDate(request.getBeginDate())
                .endDate(request.getEndDate())
                .build();
    }

    public void cancelProposal(Proposal proposal) {
        if (proposal.getRenter().id != this.id) {
            throw new DomainException("Apenas o solicitante pode cancelar a proposta.");
        }

        proposal.getVehicle().turnAvailable();
        proposal.setStatus(ProposalStatus.CANCELLED);
    }
}

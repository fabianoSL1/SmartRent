package com.ufrrj.smartrent.user.model;

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

    public Proposal createProposal(Vehicle vehicle) {
        if (vehicle.getOwner().getUser().getId() == this.user.getId()) {
            throw new RuntimeException("Owner cannot be renter");
        }

        if (!vehicle.isAvailable()) {
            throw new RuntimeException("Vehicle is not available");
        }

        return Proposal.builder()
                .renter(this)
                .vehicle(vehicle)
                .status(ProposalStatus.PENDING)
                .build();
    }

    public void cancelProposal(Proposal proposal) {
        if (proposal.getRenter().id != this.id) {
            throw new RuntimeException("Only renter can be cancelled");
        }

        proposal.getVehicle().setAvailable(true);
        proposal.setStatus(ProposalStatus.CANCELLED);
    }
}

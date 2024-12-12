package com.ufrrj.smartrent.rent.repository;

import com.ufrrj.smartrent.rent.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> getProposalByVehicleOwnerId(Long vehicleOwnerId);

    List<Proposal> getProposalByRenterId(Long renterId);
}

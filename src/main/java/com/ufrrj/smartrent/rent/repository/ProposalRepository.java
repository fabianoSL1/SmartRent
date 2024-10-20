package com.ufrrj.smartrent.rent.repository;

import com.ufrrj.smartrent.rent.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}

package com.ufrrj.smartrent.rent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.vehicle.service.VehicleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ufrrj.smartrent.common.exception.DomainException;
import com.ufrrj.smartrent.payment.model.Charge;
import com.ufrrj.smartrent.payment.service.ChargeService;
import com.ufrrj.smartrent.rent.model.Proposal;
import com.ufrrj.smartrent.rent.model.Rent;
import com.ufrrj.smartrent.rent.repository.RentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentService {

    private final ChargeService chargeService;

    private final RentRepository rentRepository;

    private final VehicleService vehicleService;


    @Transactional
    public Rent createRent(Proposal proposal) {
        if (!proposal.getStatus().equals(ProposalStatus.APPROVED)) {
            throw new DomainException("Proposta não foi aprovada.");
        }

        var rent = new Rent(proposal);
        var charge = new Charge(rent);

        rentRepository.save(rent);

        chargeService.saveCharge(charge);

        vehicleService.reserveVehicle(proposal.getVehicle());

        return rent;
    }
}

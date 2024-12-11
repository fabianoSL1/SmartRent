package com.ufrrj.smartrent.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufrrj.smartrent.payment.model.Charge;
import com.ufrrj.smartrent.payment.repository.ChargeRepository;

@Service
public class ChargeService {
    
    private ChargeRepository chargeRepository;

    public Charge saveCharge(Charge charge) {
        return chargeRepository.save(charge);
    }

    public List<Charge> chargesByRent(long rentId) {
        return chargeRepository.findAllByRentId(rentId);
    }
}

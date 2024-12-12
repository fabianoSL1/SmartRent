package com.ufrrj.smartrent.payment.service;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ufrrj.smartrent.payment.model.Charge;
import com.ufrrj.smartrent.payment.repository.ChargeRepository;

@Service
@RequiredArgsConstructor
public class ChargeService {
    
    private final ChargeRepository chargeRepository;

    @Transactional
    public Charge saveCharge(Charge charge) {
        return chargeRepository.save(charge);
    }

    public List<Charge> chargesByRent(long rentId) {
        return chargeRepository.findAllByRentId(rentId);
    }
}

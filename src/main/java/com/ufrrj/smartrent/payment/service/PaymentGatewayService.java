package com.ufrrj.smartrent.payment.service;

import com.ufrrj.smartrent.payment.enums.ChargeStatus;
import com.ufrrj.smartrent.payment.model.Charge;
import com.ufrrj.smartrent.payment.repository.ChargeRepository;
import com.ufrrj.smartrent.payment.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentGatewayService {

    private PaymentGateway paymentGateway;

    private ChargeRepository chargeRepository;

    private TransactionRepository transactionRepository;

    @Transactional()
    public void pay(Charge charge) {
        var transaction = paymentGateway.pay(charge);

        transactionRepository.save(transaction);

        if (transaction.isSuccess()) {
            charge.setStatus(ChargeStatus.PAID);
        }

        if (transaction.isRetry()) {
            charge.setStatus(ChargeStatus.REJECTED);
        }

        chargeRepository.save(charge);
    }

}

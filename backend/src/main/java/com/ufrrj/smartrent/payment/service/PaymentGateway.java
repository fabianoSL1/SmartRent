package com.ufrrj.smartrent.payment.service;


import com.ufrrj.smartrent.payment.model.Charge;
import com.ufrrj.smartrent.payment.model.Transaction;

public interface PaymentGateway {
    Transaction pay(Charge charge);
}

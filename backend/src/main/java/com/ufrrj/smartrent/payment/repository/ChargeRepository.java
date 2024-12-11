package com.ufrrj.smartrent.payment.repository;

import com.ufrrj.smartrent.payment.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
}

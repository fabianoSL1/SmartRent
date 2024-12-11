package com.ufrrj.smartrent.payment.repository;

import com.ufrrj.smartrent.payment.model.Charge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
    List<Charge> findAllByRentId(long rentId);
}

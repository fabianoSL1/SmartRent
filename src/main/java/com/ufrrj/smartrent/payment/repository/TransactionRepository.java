package com.ufrrj.smartrent.payment.repository;

import com.ufrrj.smartrent.payment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}

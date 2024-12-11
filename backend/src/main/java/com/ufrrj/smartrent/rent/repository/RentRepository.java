package com.ufrrj.smartrent.rent.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrrj.smartrent.rent.model.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}

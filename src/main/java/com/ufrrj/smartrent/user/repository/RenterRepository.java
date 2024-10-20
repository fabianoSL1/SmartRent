package com.ufrrj.smartrent.user.repository;

import com.ufrrj.smartrent.user.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RenterRepository extends JpaRepository<Renter, Long> {
}

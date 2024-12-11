package com.ufrrj.smartrent.user.repository;

import com.ufrrj.smartrent.user.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RenterRepository extends JpaRepository<Renter, Long> {
    Optional<Renter> findOwnerByUserUsername(String username);
}

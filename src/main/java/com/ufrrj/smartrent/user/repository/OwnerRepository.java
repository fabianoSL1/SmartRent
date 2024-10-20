package com.ufrrj.smartrent.user.repository;

import com.ufrrj.smartrent.user.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findOwnerByUserUsername(String username);
}

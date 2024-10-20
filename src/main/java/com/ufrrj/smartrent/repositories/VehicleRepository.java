package com.ufrrj.smartrent.repositories;

import com.ufrrj.smartrent.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

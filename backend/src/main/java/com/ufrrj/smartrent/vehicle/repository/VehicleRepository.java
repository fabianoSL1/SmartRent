package com.ufrrj.smartrent.vehicle.repository;

import com.ufrrj.smartrent.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> getVehicleByOwnerId(long ownerId);

    @Query("select v from Vehicle v where v.owner.id <> :ownerId and v.status = 'AVAILABLE'")
    List<Vehicle> getAvailableVehicles(long ownerId);
}

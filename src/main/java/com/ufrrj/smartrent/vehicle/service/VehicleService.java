package com.ufrrj.smartrent.vehicle.service;

import com.ufrrj.smartrent.user.repository.OwnerRepository;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
import com.ufrrj.smartrent.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final OwnerRepository ownerRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(long id) {
        var vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            throw new RuntimeException("Vehicle with id " + id + " not found");
        }

        return vehicle.get();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle createVehicle(String username) {
        var owner = ownerRepository.findOwnerByUserUsername(username);

        var vehicle = Vehicle.builder()
                .owner(owner)
                .build();

        return this.saveVehicle(vehicle);
    }

}

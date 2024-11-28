package com.ufrrj.smartrent.vehicle.service;

import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.user.service.OwnerService;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
import com.ufrrj.smartrent.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final OwnerService ownerService;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(long id) {
        var vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            throw new NotFoundException("Vehicle with id " + id + " not found");
        }

        return vehicle.get();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle createVehicle(String username) {
        var owner = ownerService.findOwnerByUsername(username);

        var vehicle = Vehicle.builder()
                .brand("toyota")
                .color("red")
                .identifier("TTY-8565")
                .model("SUPRA 98")
                .owner(owner)
                .build();

        return this.saveVehicle(vehicle);
    }

}

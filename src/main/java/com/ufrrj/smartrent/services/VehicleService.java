package com.ufrrj.smartrent.services;

import com.ufrrj.smartrent.utils.factories.OwnerFactory;
import com.ufrrj.smartrent.models.Vehicle;
import com.ufrrj.smartrent.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserService userService;

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
        var user = userService.findUserByUsername(username);
        var owner = OwnerFactory.createOwner(user);

        var vehicle = Vehicle.builder()
                .owner(owner)
                .build();

        return this.saveVehicle(vehicle);
    }

}

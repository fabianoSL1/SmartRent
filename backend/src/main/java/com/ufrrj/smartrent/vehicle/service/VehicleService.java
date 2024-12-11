package com.ufrrj.smartrent.vehicle.service;

import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.user.service.OwnerService;
import com.ufrrj.smartrent.vehicle.dtos.RegisterVehicleRequest;
import com.ufrrj.smartrent.vehicle.enums.VehicleStatus;
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

    public Vehicle createVehicle(RegisterVehicleRequest request) {
        var owner = ownerService.getCurrentOwner();

        var vehicle = Vehicle.builder()
                .brand(request.getBrand())
                .color(request.getColor())
                .identifier(request.getIdentifier())
                .model(request.getModel())
                .owner(owner)
                .build();

        return vehicleRepository.save(vehicle);
    }

    public Vehicle changeStatus(long id, VehicleStatus status) {
        var vehicle = this.getVehicleById(id);
        vehicle.setStatus(status);
        return vehicle;
    }
}

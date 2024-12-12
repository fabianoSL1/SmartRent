package com.ufrrj.smartrent.vehicle.service;

import com.ufrrj.smartrent.common.exception.DomainException;
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

    public List<Vehicle> getVehiclesByOwner() {
        var owner = ownerService.getCurrentOwner();
        return vehicleRepository.getVehicleByOwnerId(owner.getId());
    }

    public List<Vehicle> getAvailableVehicles() {
        var owner = ownerService.getCurrentOwner();
        return vehicleRepository.getAvailableVehicles(owner.getId());
    }

    public Vehicle getVehicleById(long id) {
        var vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            throw new NotFoundException("Veiculo não encontrado");
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
                .year(request.getYear())
                .status(VehicleStatus.AVAILABLE)
                .owner(owner)
                .build();

        return vehicleRepository.save(vehicle);
    }

    public Vehicle disableVehicle(long id) {
        return changeStatus(id, VehicleStatus.UNAVAILABLE);
    }

    public Vehicle enableVehicle(long id) {
        return changeStatus(id, VehicleStatus.AVAILABLE);
    }

    public Vehicle changeStatus(long id, VehicleStatus status) {
        var owner = ownerService.getCurrentOwner();


        var vehicle = this.getVehicleById(id);
        
        if (vehicle.getOwner().getId() != owner.getId()) {
            throw new DomainException("apenas o proprietario pode realizar está ação");
        }

        vehicle.setStatus(status);

        vehicleRepository.save(vehicle);
        
        return vehicle;
    }

    public void reserveVehicle(Vehicle vehicle) {
        vehicle.setStatus(VehicleStatus.RESERVED);
        vehicleRepository.save(vehicle);
    }
}

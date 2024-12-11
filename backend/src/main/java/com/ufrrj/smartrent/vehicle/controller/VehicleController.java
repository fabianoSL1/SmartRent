package com.ufrrj.smartrent.vehicle.controller;

import com.ufrrj.smartrent.common.security.AuthUtils;
import com.ufrrj.smartrent.vehicle.dtos.RegisterVehicleRequest;
import com.ufrrj.smartrent.vehicle.dtos.VehicleResponse;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
import com.ufrrj.smartrent.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> register(@RequestBody RegisterVehicleRequest request) {
        var username = AuthUtils.getCurrentAuthUsername();

        var vehicle = vehicleService.createVehicle(username);

        var response = createVehicleResponse(vehicle);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        var vehicles = vehicleService.getAllVehicles();

        var response = vehicles
                .stream()
                .map(this::createVehicleResponse)
                .toList();

        return ResponseEntity.ok(response);
    }


    private VehicleResponse createVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .ownerId(vehicle.getOwner().getId())
                .available(vehicle.isAvailable())
                .createdAt(vehicle.getRegisteredAt())
                .build();
    }
}

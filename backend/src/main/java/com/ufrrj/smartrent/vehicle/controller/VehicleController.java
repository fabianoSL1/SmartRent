package com.ufrrj.smartrent.vehicle.controller;

import com.ufrrj.smartrent.vehicle.dtos.ChangeStatusRequest;
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
        var vehicle = vehicleService.createVehicle(request);

        var response = createVehicleResponse(vehicle);

        return ResponseEntity.status(201).body(response);
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

    @PatchMapping("{id}")
    public ResponseEntity<VehicleResponse> changeVehicleStatus(
            @PathVariable long id,
            @RequestBody ChangeStatusRequest request) {
        var vehicle = vehicleService.changeStatus(id, request.getStatus());
        var response = createVehicleResponse(vehicle);
        return ResponseEntity.ok(response);
    }

    private VehicleResponse createVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .ownerId(vehicle.getOwner().getId())
                .status(vehicle.getStatus())
                .createdAt(vehicle.getRegisteredAt())
                .build();
    }
}

package com.ufrrj.smartrent.vehicle.controller;

import com.ufrrj.smartrent.vehicle.dtos.RegisterVehicleRequest;
import com.ufrrj.smartrent.vehicle.dtos.VehicleResponse;
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

        var response = new VehicleResponse(vehicle);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/list/owner")
    public ResponseEntity<List<VehicleResponse>> getOwnedVehicles() {
        var vehicles = vehicleService.getVehiclesByOwner();

        var response = vehicles
                .stream()
                .map(VehicleResponse::new)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/available")
    public ResponseEntity<List<VehicleResponse>> getAvailableVehicles() {
        var vehicles = vehicleService.getAvailableVehicles();

        var response = vehicles
                .stream()
                .map(VehicleResponse::new)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}/enable")
    public ResponseEntity<VehicleResponse> enableVehicle(@PathVariable long id) {
        var vehicle = vehicleService.enableVehicle(id);
        var response = new VehicleResponse(vehicle);
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("{id}/disable")
    public ResponseEntity<VehicleResponse> disableVehicle(@PathVariable long id) {
        var vehicle = vehicleService.disableVehicle(id);
        var response = new VehicleResponse(vehicle);
        return ResponseEntity.ok(response);
    }

}

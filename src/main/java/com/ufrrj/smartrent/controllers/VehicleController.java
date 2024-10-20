package com.ufrrj.smartrent.controllers;

import com.ufrrj.smartrent.dtos.vehicle.RegisterVehicleRequest;
import com.ufrrj.smartrent.dtos.vehicle.VehicleResponse;
import com.ufrrj.smartrent.services.VehicleService;
import com.ufrrj.smartrent.utils.factories.VehicleResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> register(@RequestBody RegisterVehicleRequest request) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        var username = (String) auth.getPrincipal();
        var vehicle = vehicleService.createVehicle(username);

        var response = VehicleResponseFactory.createVehicleResponse(vehicle);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        var vehicles = vehicleService.getAllVehicles();

        var response = vehicles
                .stream()
                .map(VehicleResponseFactory::createVehicleResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

}

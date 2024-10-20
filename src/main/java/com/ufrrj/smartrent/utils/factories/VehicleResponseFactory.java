package com.ufrrj.smartrent.utils.factories;

import com.ufrrj.smartrent.dtos.vehicle.VehicleResponse;
import com.ufrrj.smartrent.models.Vehicle;

public class VehicleResponseFactory {

    public static VehicleResponse createVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .ownerId(vehicle.getOwner().getId())
                .available(vehicle.isAvailable())
                .createdAt(vehicle.getCreatedAt())
                .build();
    }

}

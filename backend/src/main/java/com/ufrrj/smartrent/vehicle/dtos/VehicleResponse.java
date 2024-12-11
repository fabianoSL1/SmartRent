package com.ufrrj.smartrent.vehicle.dtos;

import lombok.Data;

import java.time.LocalDateTime;

import com.ufrrj.smartrent.vehicle.enums.VehicleStatus;
import com.ufrrj.smartrent.vehicle.model.Vehicle;

@Data
public class VehicleResponse {
    private long id;
    
    private String identifier;

    private String model;

    private String brand;

    private String Color;

    private String year;
    
    private long ownerId;

    private VehicleStatus status;

    private LocalDateTime createdAt;

    public VehicleResponse(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.identifier = vehicle.getIdentifier();
        this.model = vehicle.getModel();
        this.Color = vehicle.getColor();
        this.brand = vehicle.getBrand();
        this.year = vehicle.getYear();
        this.ownerId = vehicle.getOwner().getId();
        this.status = vehicle.getStatus();
        this.createdAt = this.getCreatedAt();
    }

}

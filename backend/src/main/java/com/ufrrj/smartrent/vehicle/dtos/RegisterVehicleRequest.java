package com.ufrrj.smartrent.vehicle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVehicleRequest {
    private String identifier;
    
    private String model;

    private String brand;

    private String color;

    private String year;
}

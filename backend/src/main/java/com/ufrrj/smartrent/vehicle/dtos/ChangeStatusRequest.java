package com.ufrrj.smartrent.vehicle.dtos;

import com.ufrrj.smartrent.vehicle.enums.VehicleStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusRequest {
    private VehicleStatus status;
}

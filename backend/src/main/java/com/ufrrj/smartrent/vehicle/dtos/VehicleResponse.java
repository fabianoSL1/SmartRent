package com.ufrrj.smartrent.vehicle.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.ufrrj.smartrent.vehicle.enums.VehicleStatus;

@Builder
@Data
public class VehicleResponse {
    private long id;

    private long ownerId;

    private VehicleStatus status;

    private LocalDateTime createdAt;

}

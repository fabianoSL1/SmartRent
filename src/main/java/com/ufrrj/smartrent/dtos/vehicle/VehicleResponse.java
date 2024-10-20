package com.ufrrj.smartrent.dtos.vehicle;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class VehicleResponse {
    private long id;

    private long ownerId;

    private boolean available;

    private LocalDateTime createdAt;

}

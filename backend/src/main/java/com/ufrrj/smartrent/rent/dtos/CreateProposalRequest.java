package com.ufrrj.smartrent.rent.dtos;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateProposalRequest {
    private long vehicleId;

    private int amount;

    private LocalDate beginDate;

    private LocalDate endDate;
}

package com.ufrrj.smartrent.rent.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ufrrj.smartrent.rent.enums.RentStatus;
import com.ufrrj.smartrent.rent.model.Rent;

import lombok.Data;

@Data
public class RentResponse {

    private long id;

    private long proposalId;

    private RentStatus status;

    private LocalDate dateBegin;

    private LocalDate dateEnd;

    private LocalDateTime registeredAt;

    public RentResponse(Rent rent) {
        this.id = rent.getId();
        this.proposalId = rent.getProposal().getId();
        this.status = rent.getStatus();
        this.dateBegin = rent.getDateBegin();
        this.dateEnd = rent.getDateEnd();
        this.registeredAt = rent.getRegisteredAt();        
    }
}

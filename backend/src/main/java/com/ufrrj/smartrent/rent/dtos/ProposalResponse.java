package com.ufrrj.smartrent.rent.dtos;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProposalResponse {

    private long id;

    private long renterId;

    private long vehicleId;

    private long ownerId;

    private ProposalStatus status;


}

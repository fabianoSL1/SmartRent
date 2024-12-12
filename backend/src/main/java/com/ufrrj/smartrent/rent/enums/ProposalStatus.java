package com.ufrrj.smartrent.rent.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ProposalStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED
}

package com.ufrrj.smartrent.rent.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RentStatus {
    PENDING_PAYMENT("pending_PAYMENT"),
    IN_PROGRESS("in_progress"),
    DONE("done");


    private final String status;

    RentStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}

package com.ufrrj.smartrent.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RentStatus {
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

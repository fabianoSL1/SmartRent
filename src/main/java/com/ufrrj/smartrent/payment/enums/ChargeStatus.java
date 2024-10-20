package com.ufrrj.smartrent.payment.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChargeStatus {
    PENDING("pending"),
    PAID("paid"),
    REJECTED("rejected");

    private final String status;

    ChargeStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}

package com.ufrrj.smartrent.models;

import com.ufrrj.smartrent.enums.ChargeStatus;
import com.ufrrj.smartrent.enums.RentStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Proposal proposal;

    private RentStatus status;

    @OneToMany
    private List<Charge> charges;

    public int getTotalAmount() {
        var amount = 0;

        for (Charge charge : charges) {
            amount += charge.getAmount();
        }

        return amount;
    }

    public int getPaidAmount() {
        var amount = 0;

        for (Charge charge : charges) {
            if (charge.getStatus().equals(ChargeStatus.PAID)) {
                amount += charge.getAmount();
            }
        }

        return amount;
    }

}

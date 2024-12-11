package com.ufrrj.smartrent.rent.model;

import com.ufrrj.smartrent.payment.enums.ChargeStatus;
import com.ufrrj.smartrent.rent.enums.RentStatus;
import com.ufrrj.smartrent.payment.model.Charge;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity()
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Proposal proposal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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

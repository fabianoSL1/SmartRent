package com.ufrrj.smartrent.payment.model;

import com.ufrrj.smartrent.payment.enums.ChargeStatus;
import com.ufrrj.smartrent.rent.model.Rent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity()
@Table(name = "charges")
@AllArgsConstructor
@NoArgsConstructor
public class Charge {

    public Charge(Rent rent) {
        this.rent = rent;
        this.amount = rent.getProposal().getAmount();
        this.status = ChargeStatus.PENDING;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(optional = false)
    private Rent rent;

    @OneToMany
    private List<Transaction> transactions;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargeStatus status;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

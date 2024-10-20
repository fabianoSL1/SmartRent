package com.ufrrj.smartrent.models;

import com.ufrrj.smartrent.enums.ChargeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity(name = "charges")
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int amount;

    @ManyToOne
    private Rent rent;

    private ChargeStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

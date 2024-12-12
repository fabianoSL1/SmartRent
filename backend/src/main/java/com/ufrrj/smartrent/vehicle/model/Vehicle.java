package com.ufrrj.smartrent.vehicle.model;

import com.ufrrj.smartrent.user.model.Owner;
import com.ufrrj.smartrent.vehicle.enums.VehicleStatus;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private Owner owner;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String year;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime registeredAt;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    public boolean isAvailable() {
        return this.status.equals(VehicleStatus.AVAILABLE);
    }

    public void turnAvailable() {
        this.status = VehicleStatus.AVAILABLE;
    }

    public void turnReserved() {
        this.status = VehicleStatus.RESERVED;
    }

    public void turnInRent() {
        this.status = VehicleStatus.IN_RENT;
    }
}

package com.ufrrj.smartrent.vehicle.model;

import com.ufrrj.smartrent.user.model.Owner;
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

    @CreationTimestamp
    private LocalDateTime registerAt;

    @Setter
    private boolean available;

}

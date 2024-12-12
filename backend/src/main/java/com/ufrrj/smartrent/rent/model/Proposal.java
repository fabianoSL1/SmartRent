package com.ufrrj.smartrent.rent.model;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.user.model.Renter;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private Renter renter;

    @ManyToOne(optional = false)
    private Vehicle vehicle;

    @Setter
    @Column(nullable = false)
    private int amount;

    @Setter
    @Column(nullable = false)
    private LocalDate beginDate;

    @Setter
    @Column(nullable = false)
    private LocalDate endDate;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

}

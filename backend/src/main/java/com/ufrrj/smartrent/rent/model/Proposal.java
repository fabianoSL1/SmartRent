package com.ufrrj.smartrent.rent.model;

import com.ufrrj.smartrent.rent.enums.ProposalStatus;
import com.ufrrj.smartrent.user.model.Renter;
import com.ufrrj.smartrent.vehicle.model.Vehicle;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(optional = false)
    private Renter renter;

    @OneToOne(optional = false)
    private Vehicle vehicle;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

}

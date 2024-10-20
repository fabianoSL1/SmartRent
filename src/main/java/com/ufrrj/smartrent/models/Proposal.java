package com.ufrrj.smartrent.models;

import com.ufrrj.smartrent.enums.ProposalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Entity(name = "proposals")
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Renter renter;

    @OneToOne
    private Vehicle vehicle;

    @Setter
    @Column(nullable = false)
    private ProposalStatus status;

}

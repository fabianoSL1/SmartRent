package com.ufrrj.smartrent.models;

import com.ufrrj.smartrent.enums.ProposalStatus;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity(name = "proposals")
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Renter renter;

    @OneToOne
    private Vehicle vehicle;

    @Setter
    @Column(nullable = false)
    private ProposalStatus status;

}

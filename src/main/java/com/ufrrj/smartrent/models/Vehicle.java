package com.ufrrj.smartrent.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity(name = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Para utilizar a classe Owner diretamente precisaria ter um
     * uma coluna para discriminar na tabela users.
     * */
    @ManyToOne
    private User owner;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Setter
    private boolean available;

}

package com.ufrrj.smartrent.payment.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false)
    private boolean retry;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String code;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime timestamp;
}

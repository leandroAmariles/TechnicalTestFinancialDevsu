package com.devsu.audittransactions.infraestructure.adapters.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION_AUDIT")
public class TransactionAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;

    private LocalDateTime createDate;

    private String clientName;

    private String accountNumber;

    private String transactionType;

    private double initialBalance;

    private boolean status;

    private double transactionAmount;

    private double balance;
}

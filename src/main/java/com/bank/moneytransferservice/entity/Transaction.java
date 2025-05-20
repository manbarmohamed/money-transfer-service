package com.bank.moneytransferservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id @GeneratedValue
    private Long id;

    private String type; // "TRANSFER"
    private Double amount;
    private LocalDateTime date;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;
}

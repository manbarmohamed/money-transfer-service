package com.bank.moneytransferservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
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

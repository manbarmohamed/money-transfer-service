package com.bank.moneytransferservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")

public class Account {
    @Id @GeneratedValue
    private Long id;
    private String accountNumber;
    private Double balance;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> outgoingTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> incomingTransactions = new ArrayList<>();
}

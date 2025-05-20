package com.bank.moneytransferservice.entity;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id @GeneratedValue
    private Long id;
    private String accountNumber;
    private Double balance;

    @ManyToOne
    private User owner;
}

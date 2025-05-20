package com.bank.moneytransferservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;
}

package com.bank.moneytransferservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;
}

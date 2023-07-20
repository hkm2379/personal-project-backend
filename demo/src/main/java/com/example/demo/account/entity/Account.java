package com.example.demo.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
public class Account {

    @Id
    @Getter
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Getter
    private String password;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private AccountRole accountRole;

    private String address;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
        accountRole.setAccount(this);
    }
}

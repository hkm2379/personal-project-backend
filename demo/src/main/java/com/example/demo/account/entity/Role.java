package com.example.demo.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roleType")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleType getRoleType () {
        return roleType;
    }
}

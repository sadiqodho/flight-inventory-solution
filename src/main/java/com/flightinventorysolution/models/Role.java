package com.flightinventorysolution.models;

import com.flightinventorysolution.types.RoleType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;

    public Role() {}
    public Role(RoleType name) {
        this.name = name;
    }
}
package com.javier.app_security.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable {
    @Id
    private BigInteger id;
    private String email;
    @Column(name = "pwd")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer")
    private List<RoleEntity> roles;
}

package com.payment.customer.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "phone_number", nullable = false, unique = true)
    private int phoneNumber;
}

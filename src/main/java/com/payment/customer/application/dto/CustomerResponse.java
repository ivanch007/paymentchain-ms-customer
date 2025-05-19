package com.payment.customer.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private int phoneNumber;
}

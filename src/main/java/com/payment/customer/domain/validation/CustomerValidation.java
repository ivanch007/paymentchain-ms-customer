package com.payment.customer.domain.validation;

import com.payment.customer.domain.exceptions.customerExceptions.CustomerInvalidNameException;
import com.payment.customer.domain.model.Customer;
import com.payment.customer.domain.util.constants.CustomerConstants;

public class CustomerValidation {

    private CustomerValidation() {
        // Private constructor to prevent instantiation
    }

    public static void validateName(Customer customer){
        if(customer.getName() == null || customer.getName().isBlank()
        || customer.getName().length() > CustomerConstants.MAX_NAME_LENGTH){
            throw new CustomerInvalidNameException();
        }
    }

}

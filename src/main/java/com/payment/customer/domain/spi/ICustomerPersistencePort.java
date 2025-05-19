package com.payment.customer.domain.spi;

import com.payment.customer.domain.model.Customer;

import java.util.List;

public interface ICustomerPersistencePort {
    void saveCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers(Integer page, Integer size, String sortingType);

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    boolean emailAlreadyExist(String email);

    boolean phoneNumberAlreadyExist(Integer phoneNumber);

    boolean isEmailUsedByAnotherCustomer(String email, Long id);

    boolean isPhoneNumberUsedByAnotherCustomer(Integer phoneNumber, Long id);


    Long countTotalCustomers();
}

package com.payment.customer.domain.api;

import com.payment.customer.domain.model.Customer;
import com.payment.customer.domain.util.pagination.PagedResult;

import java.util.List;

public interface ICustomerServicePort {
    void saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    PagedResult<Customer> getAllCustomers(Integer page, Integer size, String sortingType);
    void updateCustomer(Customer customer);
    void deleteCustomer(Long id);



}

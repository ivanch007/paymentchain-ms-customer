package com.payment.customer.application.handler;

import com.payment.customer.application.dto.CustomerRequest;
import com.payment.customer.application.dto.CustomerResponse;
import com.payment.customer.domain.util.pagination.PagedResult;

public interface ICustomerHandler {

    void saveCustomer(CustomerRequest customerRequest);
    PagedResult<CustomerResponse> getAllCustomers(Integer page, Integer size, String sortingType);
    CustomerResponse getCustomerById(Long id);
    void updateCustomer(Long id, CustomerRequest customerRequest);
    void deleteCustomer(Long id);
}

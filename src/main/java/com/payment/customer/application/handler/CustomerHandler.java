package com.payment.customer.application.handler;

import com.payment.customer.application.dto.CustomerRequest;
import com.payment.customer.application.dto.CustomerResponse;
import com.payment.customer.application.mapper.ICustomerRequestMapper;
import com.payment.customer.application.mapper.ICustomerResponseMapper;
import com.payment.customer.domain.api.ICustomerServicePort;
import com.payment.customer.domain.model.Customer;
import com.payment.customer.domain.util.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerHandler implements ICustomerHandler{

    private final ICustomerRequestMapper customerRequestMapper;
    private final ICustomerResponseMapper customerResponseMapper;
    private final ICustomerServicePort customerServicePort;



    @Override
    public void saveCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRequestMapper.toCustomer(customerRequest);
        customerServicePort.saveCustomer(customer);
    }

    @Override
    public PagedResult<CustomerResponse> getAllCustomers(Integer page, Integer size, String sortingType) {

        PagedResult<Customer> pagedResult = customerServicePort.getAllCustomers(page, size, sortingType);
        List<CustomerResponse> customerResponseList = customerResponseMapper.toResponseList(pagedResult.getContent());
        return new PagedResult<>(
                customerResponseList,
                pagedResult.getPage(),
                pagedResult.getSize(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.isLast()
        );
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerServicePort.getCustomerById(id);
        return customerResponseMapper.toCustomerResponse(customer);
    }

    @Override
    public void updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRequestMapper.toCustomer(customerRequest);
        customer.setId(id);
        customerServicePort.saveCustomer(customer);

    }

    @Override
    public void deleteCustomer(Long id) {

        customerServicePort.deleteCustomer(id);


    }
}

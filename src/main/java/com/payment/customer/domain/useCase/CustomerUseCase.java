package com.payment.customer.domain.useCase;

import com.payment.customer.domain.api.ICustomerServicePort;
import com.payment.customer.domain.exceptions.customerExceptions.EmailAlReadyExist;
import com.payment.customer.domain.exceptions.customerExceptions.PhoneNumberAlreadyExist;
import com.payment.customer.domain.model.Customer;
import com.payment.customer.domain.spi.ICustomerPersistencePort;
import com.payment.customer.domain.util.pagination.PagedResult;
import com.payment.customer.domain.validation.CustomerValidation;
import com.payment.customer.domain.validation.ValidateSortingType;

import java.util.List;

public class CustomerUseCase implements ICustomerServicePort {

   private final ICustomerPersistencePort customerPersistencePort;

    public CustomerUseCase(ICustomerPersistencePort customerPersistencePort) {
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public void saveCustomer(Customer customer) {

        CustomerValidation.validateName(customer);

        if(customerPersistencePort.emailAlreadyExist(customer.getEmail())){
            throw new EmailAlReadyExist();
        } else if (customerPersistencePort.phoneNumberAlreadyExist(customer.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExist();
        }
        customerPersistencePort.saveCustomer(customer);

    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerPersistencePort.getCustomerById(id);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
        return customer;

    }

    @Override
    public PagedResult<Customer> getAllCustomers(Integer page, Integer size, String sortingType) {

        ValidateSortingType.validateSortingType(sortingType);

        List<Customer> customers = customerPersistencePort.getAllCustomers(page, size, sortingType);
        long totalElements = customerPersistencePort.countTotalCustomers();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean last = page == totalPages - 1;

        PagedResult<Customer> pagedResult = new PagedResult<>(customers, page, size, totalElements, totalPages, last);
        pagedResult.setContent(customers);
        pagedResult.setPage(page);
        pagedResult.setSize(size);
        pagedResult.setTotalElements(totalElements);
        pagedResult.setTotalPages(totalPages);
        pagedResult.setLast(page == totalPages -1);


        return pagedResult;
    }

    @Override
    public void updateCustomer(Customer customer) {
        CustomerValidation.validateName(customer);

        if (customerPersistencePort.isEmailUsedByAnotherCustomer(customer.getEmail(), customer.getId())) {
            throw new EmailAlReadyExist();
        }

        if (customerPersistencePort.isPhoneNumberUsedByAnotherCustomer(customer.getPhoneNumber(), customer.getId())) {
            throw new PhoneNumberAlreadyExist();
        }

        customerPersistencePort.updateCustomer(customer);
    }


    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerPersistencePort.getCustomerById(id);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
        customerPersistencePort.deleteCustomer(id);

    }


}

package com.payment.customer.infrastructure.output.jpa.adapter;

import com.payment.customer.domain.exceptions.customerExceptions.EmailAlReadyExist;
import com.payment.customer.domain.exceptions.customerExceptions.NoDataFound;
import com.payment.customer.domain.exceptions.customerExceptions.PhoneNumberAlreadyExist;
import com.payment.customer.domain.model.Customer;
import com.payment.customer.domain.spi.ICustomerPersistencePort;
import com.payment.customer.infrastructure.output.jpa.entity.CustomerEntity;
import com.payment.customer.infrastructure.output.jpa.mapper.ICustomerEntityMapper;
import com.payment.customer.infrastructure.output.jpa.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;


@RequiredArgsConstructor
public class CustomerJpaAdapter implements ICustomerPersistencePort {

    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;

    @Override
    public void saveCustomer(Customer customer) {
         if(customerRepository.findByEmail(customer.getEmail()).isPresent()){
             throw new EmailAlReadyExist();
         }
         if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()){
             throw new PhoneNumberAlreadyExist();
         }
            customerRepository.save(customerEntityMapper.toEntity(customer));
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerEntityMapper::toCustomer)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers(Integer page, Integer size, String sortingType) {
        Pageable pagination = PageRequest.of(page, size, getSortingType(sortingType));
        List<CustomerEntity> customerEntityList = customerRepository.findAll(pagination).getContent();
        if(customerEntityList.isEmpty()){
            throw new NoDataFound();
        }
        return customerEntityMapper.toCustomerList(customerEntityList);
    }

    private Sort getSortingType(String sortingType) {
        if (sortingType.equals("asc"))
            return Sort.by("name").ascending();

        return Sort.by("name").descending();
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customerRepository.findById(customer.getId()).isPresent()) {
            customerRepository.save(customerEntityMapper.toEntity(customer));
        }
        if(customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new EmailAlReadyExist();
        }
        if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()) {
            throw new PhoneNumberAlreadyExist();
        }
        else {
            throw new NoDataFound();
        }

    }

    @Override
    public void deleteCustomer(Long id) {

        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new NoDataFound();
        }

    }

    @Override
    public boolean emailAlreadyExist(String email) {
        return false;
    }

    @Override
    public boolean phoneNumberAlreadyExist(Integer phoneNumber) {
        return false;
    }

    @Override
    public Long countTotalCustomers() {
        return customerRepository.count();
    }
}

package com.payment.customer.infrastructure.input.rest;

import com.payment.customer.application.dto.CustomerRequest;
import com.payment.customer.application.dto.CustomerResponse;
import com.payment.customer.application.handler.ICustomerHandler;
import com.payment.customer.domain.util.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerHandler customerHandler;

    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerRequest customerRequest){
        customerHandler.saveCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PagedResult<CustomerResponse>> getAllCustomers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sortingType){

        PagedResult<CustomerResponse> response = customerHandler.getAllCustomers(page, size, sortingType);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerHandler.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest){
        customerHandler.updateCustomer(id, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerHandler.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

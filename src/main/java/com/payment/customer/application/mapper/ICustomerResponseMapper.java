package com.payment.customer.application.mapper;

import com.payment.customer.application.dto.CustomerResponse;
import com.payment.customer.domain.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ICustomerResponseMapper {

    CustomerResponse toCustomerResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customerList);

}

package com.payment.customer.application.mapper;

import com.payment.customer.application.dto.CustomerRequest;
import com.payment.customer.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ICustomerRequestMapper {

    Customer toCustomer(CustomerRequest customerRequest);


}

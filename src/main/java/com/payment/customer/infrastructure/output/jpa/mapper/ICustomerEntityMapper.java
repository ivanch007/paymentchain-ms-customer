package com.payment.customer.infrastructure.output.jpa.mapper;

import com.payment.customer.domain.model.Customer;
import com.payment.customer.infrastructure.output.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICustomerEntityMapper {
    CustomerEntity toEntity(Customer customer);
    Customer toCustomer(CustomerEntity customerEntity);
    List<Customer> toCustomerList(List<CustomerEntity> customerEntityList);

}

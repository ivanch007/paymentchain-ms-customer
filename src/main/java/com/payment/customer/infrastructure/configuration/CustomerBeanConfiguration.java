package com.payment.customer.infrastructure.configuration;

import com.payment.customer.domain.api.ICustomerServicePort;
import com.payment.customer.domain.spi.ICustomerPersistencePort;
import com.payment.customer.domain.useCase.CustomerUseCase;
import com.payment.customer.infrastructure.output.jpa.adapter.CustomerJpaAdapter;
import com.payment.customer.infrastructure.output.jpa.mapper.ICustomerEntityMapper;
import com.payment.customer.infrastructure.output.jpa.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerBeanConfiguration {

    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;

    @Bean
    public ICustomerPersistencePort customerPersistencePort(){
        return new CustomerJpaAdapter(customerRepository, customerEntityMapper);
    }

    @Bean
    public ICustomerServicePort customerServicePort(){
        return new CustomerUseCase(customerPersistencePort());
    }

}

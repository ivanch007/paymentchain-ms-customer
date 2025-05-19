package com.payment.customer.infrastructure.output.jpa.repository;

import com.payment.customer.infrastructure.output.jpa.entity.CustomerEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @NotNull
    Optional<CustomerEntity> findById(Long id);
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByPhoneNumber(Integer phoneNumber);
    Page<CustomerEntity> findAll(Pageable pageable);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByPhoneNumberAndIdNot(Integer phoneNumber, Long id);

}

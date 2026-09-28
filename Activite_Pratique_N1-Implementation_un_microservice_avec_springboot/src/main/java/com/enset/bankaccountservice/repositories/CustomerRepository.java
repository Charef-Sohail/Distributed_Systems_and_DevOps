package com.enset.bankaccountservice.repositories;

import com.enset.bankaccountservice.entities.BankAccount;
import com.enset.bankaccountservice.entities.Customer;
import com.enset.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
